(ns moc.handler.login
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-handler]]))

(register-handler
 :login/set-email
 (fn [db [_ value]]
   (assoc-in db [:ui :user/login :email] value)))

(register-handler
 :login/set-password
 (fn [db [_ value]]
   (assoc-in db [:ui :user/login :password] value)))

(register-handler
 :login/reset-state
 (fn [db _]
   (assoc-in db [:ui :user/login] {:email ""
                                   :password ""
                                   :errors {}})))

(register-handler
 :login/set-errors
 (fn [db [_ errors]]
   (assoc-in db [:ui :user/login :errors] errors)))

(register-handler
 :login/reset-errors
 (fn [db _]
   (assoc-in db [:ui :user/login :errors] {})))
