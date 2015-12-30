(ns moc.routes.dispatch
  (:require [ring.util.response :as response]))

(defmulti routes :bidi/id)

(defmethod routes :default [req]
  (-> (response/resource-response "index.html" {:root "public"})
      (response/content-type "text/html")))
