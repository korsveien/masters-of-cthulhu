(ns moc.routes.dispatch
  (:require [ring.util.response :as response]))

(defmulti routes :route/id)

(defmethod routes :default [{:keys [request-method]}]
  (if (= :get request-method)
    (-> (response/resource-response "index.html" {:root "public"})
        (response/content-type "text/html"))
    {:status 404}))
