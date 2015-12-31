(ns moc.routes.user
  (:require [moc.routes.dispatch :refer [routes]]))

(defmethod routes :api.user/register [req]
  {:status 400})

(defmethod routes :api.user/login [req]
  {:status 400})
