(ns moc.db
  (:require [re-frame.core :refer [register-handler]]))

(def initial-state {:loading/count 0
                    :route/info nil
                    :user/current nil
                    :ui {}})

(register-handler
 :app/initialize
 (fn [_ _]
   initial-state))
