(ns moc.db
  (:require [re-frame.core :refer [register-handler]]))

(def initial-state {:loading? 0
                    :ui {:user/login {:email ""
                                      :password ""
                                      :errors {}}
                         :user/register {:email ""
                                         :password ""
                                         :confirm-password ""
                                         :errors {}}}})

(register-handler
 :app/initialize
 (fn [_ _]
   initial-state))
