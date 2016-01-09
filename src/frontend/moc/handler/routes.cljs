(ns moc.handler.routes
  (:require [re-frame.core :refer [register-handler]]))

(register-handler
 :route/set-info
 (fn [db [_ route-info]]
   (assoc db :route/info route-info)))
