(ns moc.ui.authentication.register
  (:require [reagent.core :as reagent]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as user]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]
            [moc.ui.common.handlers :refer [pass-to-state!]]))

(defn on-register [state-atom error-atom]
  (let [errors (user/validate-register-schema @state-atom)]
    (if errors
      (reset! error-atom errors)
      (do
        (reset! error-atom {})
        (println "valid")))))

(defn footer []
  [:span
   "Are you a user? "
   [link {:path [:url.user/login]} "Log in"]])

(defn register [_]
  (let [state (reagent/atom {})
        errors (reagent/atom {})]
    (fn [_]
      [:div.register-page {:on-key-up #(when (= 13 (-> % .-keyCode))
                                         (on-register state errors))}
       [:h1.logo "Masters of Cthulhu"]
       [box {:title "Register"
             :footer [footer]}
        [icon-input {:icon "user"
                     :auto-focus true
                     :placeholder "Email"
                     :value (:email @state)
                     :error (:email @errors)
                     :on-change (pass-to-state! state :email)}]
        [icon-input {:icon "lock"
                     :type "password"
                     :placeholder "Password"
                     :value (:password @state)
                     :error (:password @errors)
                     :on-change (pass-to-state! state :password)}]
        [icon-input {:icon "lock"
                     :type "password"
                     :placeholder "Confirm password"
                     :value (:confirm-password @state)
                     :error (:confirm-password @errors)
                     :on-change (pass-to-state! state :confirm-password)}]
        [button {:loading? (:loading? @state)
                 :on-click #(on-register state errors)}
         "Register"]]])))
