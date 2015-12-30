(ns moc.ui.authentication.login
  (:require [reagent.core :as reagent]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as user]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]
            [moc.ui.common.handlers :refer [pass-to-state!]]))

(defn footer []
  [:span
   "Not a user? "
   [link {:path [:url.user/register]} "Sign up"]])

(defn on-login [state-atom error-atom]
  (let [errors (validate @state-atom user/login-schema)]
    (if errors
      (reset! error-atom errors)
      (do
        (reset! error-atom {})
        (println "valid")))))

(defn login [_]
  (let [state (reagent/atom {})
        errors (reagent/atom {})]
    (fn [_]
      [:div.login-page {:on-key-up #(when (= 13 (-> % .-keyCode))
                                      (on-login state errors))}
       [:h1.logo "Masters of Cthulhu"]
       [box {:title "Login"
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
        [button {:loading? (:loading? @state)
                 :on-click #(on-login state errors)}
         "Log in"]]])))
