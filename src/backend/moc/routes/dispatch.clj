(ns moc.routes.dispatch
  (:require [ring.util.response :as response]))

(defmulti dispatch :bidi/id)

(defmethod dispatch :default [_]
  (-> (response/resource-response "index.html" {:root "public"})
      (response/content-type "text/html")))
