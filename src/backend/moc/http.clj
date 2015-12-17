(ns moc.http
  (:require [com.stuartsierra.component :as component]
            [bidi.bidi :as bidi]
            [aleph.http :as http]
            [moc.log :as log]
            [moc.urls :refer [urls]]
            [moc.routes.dispatch :refer [dispatch]])
  (:import java.util.concurrent.Executors
           io.netty.channel.ChannelPipeline
           io.netty.handler.codec.http.HttpContentCompressor))

(defn- wrap-error-page
  [handler]
  (fn [req]
    (try
      (handler req)
      (catch Exception e
        (log/exception e)
        {:status 500}))))

(defn- wrap-components [handler envars db]
  (fn [req]
    (handler (assoc req
                    :component/db (:spec db)
                    :component/envars envars))))

(defn- router [req]
  (let [{:keys [handler route-params]} (bidi/match-route urls (:uri req))]
    (dispatch (-> req
                  (assoc :bidi/id handler)
                  (update :params merge route-params)))))

(defn- wrap-app [envars db]
  (-> router
      (wrap-components envars db)
      (wrap-error-page)
      (log/wrap-with-logger)))

(defn- add-http-compressor! [^ChannelPipeline pipeline]
  (doto pipeline
    (.addBefore "request-handler" "deflater" (HttpContentCompressor.))))

(defrecord Server [server envars db]
  component/Lifecycle
  (start [self]
    (if server
      self
      (let [{:keys [port cpus]} envars
            pool-size (min 8 (* 2 (max 1 cpus)))
            app (wrap-app envars db)]
        (log/info (str "Starting server with " pool-size " threads"))
        (assoc self :server (http/start-server app {:port port
                                                    :executor (Executors/newFixedThreadPool pool-size)
                                                    :pipeline-transform add-http-compressor!})))))

  (stop [self]
    (log/info "Shutting down server")
    (when server
      (.close server))
    (assoc self :server nil)))
