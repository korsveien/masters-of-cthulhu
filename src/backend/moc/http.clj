(ns moc.http
  (:require [com.stuartsierra.component :as component]
            [bidi.bidi :as bidi]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.not-modified :refer [wrap-not-modified]]
            [ring.middleware.resource :refer [wrap-resource]]
            [moc.log :as log]
            [moc.urls :refer [urls]]
            [moc.routes.dispatch :refer [dispatch]]))

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
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)
      (wrap-error-page)
      (log/wrap-with-logger)))

(defrecord Server [server envars db]
  component/Lifecycle
  (start [self]
    (if server
      self
      (let [{:keys [port cpus]} envars
            pool-size (min 16 (* 2 (max 2 cpus)))
            app (wrap-app envars db)]
        (log/info (str "Starting server with " pool-size " threads"))
        (assoc self :server (run-jetty app {:port port
                                            :join? false
                                            :max-threads pool-size
                                            :min-threads pool-size})))))

  (stop [self]
    (log/info "Shutting down server")
    (when server
      (.stop server))
    (assoc self :server nil)))
