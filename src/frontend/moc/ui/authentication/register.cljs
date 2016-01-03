(ns moc.ui.authentication.register
  (:require [re-frame.core :refer [dispatch dispatch-sync subscribe]]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]
            [moc.ui.common.handlers :refer [pass-to-dispatch]]))

(defn footer []
  [:span
   "Already have a password? "
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
       [box {:title "Login"
             :footer [footer]}
        [icon-input {:icon "user"
                     :auto-focus true
                     :placeholder "Email"
                     :disabled? (:success? @state)
                     :value (:email @state)
                     :error (:email @errors)
                     :on-change (pass-to-dispatch :register/set-email)}]
        (if (:success? @state)
          [:div "Success! An email should arrive shortly with further instructions."]
          [button {:loading? @loading?
                   :on-click #(dispatch [:register/send @state])}
           "Get login link"])]])))
