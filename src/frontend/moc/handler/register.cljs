(ns moc.handler.register
  (:require [re-frame.core :refer [dispatch register-handler]]
            [moc.ajax :as ajax]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as user]))

;;; FORM STATE

(register-handler
 :register/set-email
 (fn [db [_ value]]
   (assoc-in db [:ui :user/register :email] value)))

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
   (let [errors (validate data user/register-schema)]
     (if errors
       (dispatch [:register/set-errors errors])
       (do
         (dispatch [:register/reset-errors])
         (ajax/request {:path [:api.auth/register]
                        :data data
                        :on-success #(dispatch [:register/send-success %])}))))
   db))

(register-handler
 :register/send-success
 (fn [db [_ data]]
   (assoc-in db [:ui :user/register :success?] true)))
