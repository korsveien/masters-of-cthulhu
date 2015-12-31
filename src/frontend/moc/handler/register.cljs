(ns moc.handler.register
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch register-handler]]
            [moc.ajax :as ajax]
            [moc.validate.user :as user]))

;;; FORM STATE

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


;;; SERVER COMMUNICATION

(register-handler
 :register/send
 (fn [db [_ data]]
   (let [errors (user/validate-register-schema data)]
     (if errors
       (dispatch [:register/set-errors errors])
       (do
         (dispatch [:register/reset-errors])
         (ajax/request {:path [:api.user/register]
                        :data data
                        :on-success #(dispatch [:register/send-success %])
                        :on-fail #(dispatch [:register/send-failed %])}))))
   db))

(register-handler
 :register/send-success
 (fn [db [_ data]]
   (println data)
   db))

(register-handler
 :register/send-failed
 (fn [db [_ data]]
   (println data)
   db))
