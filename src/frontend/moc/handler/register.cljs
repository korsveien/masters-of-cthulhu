(ns moc.handler.register
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-handler]]))

(register-handler
 :register/set-email
 (fn [db [_ value]]
   (assoc-in db [:ui :user/register :email] value)))

(register-handler
 :register/set-password
 (fn [db [_ value]]
   (assoc-in db [:ui :user/register :password] value)))

(register-handler
 :register/set-confirm-password
 (fn [db [_ value]]
   (assoc-in db [:ui :user/register :confirm-password] value)))

(register-handler
 :register/reset-state
 (fn [db _]
   (assoc-in db [:ui :user/register] {:email ""
                                      :password ""
                                      :confirm-password ""
                                      :errors {}})))

(register-handler
 :register/set-errors
 (fn [db [_ errors]]
   (assoc-in db [:ui :user/register :errors] errors)))

(register-handler
 :register/reset-errors
 (fn [db _]
   (assoc-in db [:ui :user/register :errors] {})))
