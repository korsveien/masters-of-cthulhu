(ns moc.ui.authentication.register
  (:require [re-frame.core :refer [dispatch dispatch-sync subscribe]]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]
            [moc.ui.common.handlers :refer [pass-to-dispatch]]))

(defn footer []
  [:span
   "Are you a user? "
   [link {:path [:url.user/login]} "Log in"]])

(defn register [_]
  (dispatch-sync [:register/reset-state])
  (let [loading? (subscribe [:loading?])
        state (subscribe [:register/form-state])
        errors (subscribe [:register/form-errors])]
    (fn [_]
      [:div.register-page {:on-key-up #(when (= 13 (-> % .-keyCode))
                                         (dispatch [:register/send @state]))}
       [:h1.logo "Masters of Cthulhu"]
       [box {:title "Register"
             :footer [footer]}
        [icon-input {:icon "user"
                     :auto-focus true
                     :placeholder "Email"
                     :value (:email @state)
                     :error (:email @errors)
                     :on-change (pass-to-dispatch :register/set-email)}]
        [icon-input {:icon "lock"
                     :type "password"
                     :placeholder "Password"
                     :value (:password @state)
                     :error (:password @errors)
                     :on-change (pass-to-dispatch :register/set-password)}]
        [icon-input {:icon "lock"
                     :type "password"
                     :placeholder "Confirm password"
                     :value (:confirm-password @state)
                     :error (:confirm-password @errors)
                     :on-change (pass-to-dispatch :register/set-confirm-password)}]
        [button {:loading? @loading?
                 :on-click #(dispatch [:register/send @state])}
         "Register"]]])))
