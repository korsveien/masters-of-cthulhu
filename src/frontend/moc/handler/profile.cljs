(ns moc.handler.profile
  (:require [re-frame.core :refer [dispatch register-handler]]
            [moc.ajax :as ajax]
            [moc.validate.user :refer [validate-profile]]))

;;; FORM STATE

(register-handler
 :profile/reset-state
 (fn [db _]
   (let [user (:user/current db)]
     (assoc-in db [:ui :user/profile] {:name (:name user)
                                       :email (:email user)
                                       :change-password? (not (:password user))
                                       :password nil
                                       :confirm-password nil
                                       :success? false
                                       :errors {}}))))

(register-handler
 :profile/set-name
 (fn [db [_ value]]
   (assoc-in db [:ui :user/profile :name] value)))

(register-handler
 :profile/set-email
 (fn [db [_ value]]
   (assoc-in db [:ui :user/profile :email] value)))

(register-handler
 :profile/set-change-password?
 (fn [db [_ value]]
   (assoc-in db [:ui :user/profile :change-password?] value)))

(register-handler
 :profile/set-password
 (fn [db [_ value]]
   (assoc-in db [:ui :user/profile :password] value)))

(register-handler
 :profile/set-confirm-password
 (fn [db [_ value]]
   (assoc-in db [:ui :user/profile :confirm-password] value)))


(register-handler
 :profile/set-errors
 (fn [db [_ errors]]
   (assoc-in db [:ui :user/profile :errors] errors)))

(register-handler
 :profile/reset-errors
 (fn [db _]
   (assoc-in db [:ui :user/profile :errors] {})))

;;; SERVER COMMUNICATION

(register-handler
 :profile/send
 (fn [db [_ data]]
   (let [errors (validate-profile data)]
     (if errors
       (dispatch [:profile/set-errors errors])
       (do
         (dispatch [:profile/reset-errors])
         (ajax/request {:path [:api.user/profile]
                        :data (dissoc data :change-password? :confirm-password)
                        :on-success #(dispatch [:profile/send-success %])
                        :on-fail #(dispatch [:profile/set-errors %])}))))
   db))

(register-handler
 :profile/send-success
 (fn [db [_ user]]
   (-> db
       (assoc :user/current user)
       (assoc-in [:ui :user/profile :success?] true))))
