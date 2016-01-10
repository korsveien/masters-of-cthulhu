(ns moc.db
  (:require [re-frame.core :refer [register-handler]]))

(def initial-state {:loading/count 0
                    :route/info nil
                    :user/current nil
                    :ui {:user/login {:email ""
                                      :password ""
                                      :errors {}}
                         :user/register {:email ""
                                         :success? false
                                         :errors {}}}})

(register-handler
 :app/initialize
 (fn [_ _]
   initial-state))
