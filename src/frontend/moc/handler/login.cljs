(ns moc.handler.login
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [dispatch register-handler]]
            [moc.ajax :as ajax]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as user]))

;;; FORM STATE

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

;;; SERVER COMMUNICATION

(register-handler
 :login/send
 (fn [db [_ data]]
   (let [errors (validate data user/login-schema)]
     (if errors
       (dispatch [:login/set-errors errors])
       (do
         (dispatch [:login/reset-errors])
         (ajax/request {:path [:api.user/login]
                        :data data
                        :on-success #(dispatch [:login/send-success %])
                        :on-fail #(dispatch [:login/send-failed %])}))))
   db))

(register-handler
 :login/send-success
 (fn [db [_ data]]
   (println data)
   db))

(register-handler
 :login/send-failed
 (fn [db [_ data]]
   (println data)
   db))
