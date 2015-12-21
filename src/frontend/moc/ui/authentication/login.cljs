(ns moc.ui.authentication.login
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as user]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]))

(defui Login
  Object
  (onEmailUpdate [this value]
    (om/update-state! this assoc :email value))

  (onPasswordUpdate [this value]
    (om/update-state! this assoc :password value))

  (onLogin [this]
    (if-let [errors (validate (om/get-state this) user/login-schema)]
      (om/update-state! this assoc :errors errors)
      (do
        (om/update-state! this assoc :errors {})
        (println "valid"))))

  (footer [_]
    (dom/span nil
              "Not a user? "
              (link {:path [:user/register]} "Sign up")))

  (render [this]
    (let [{:keys [email password errors]} (om/get-state this)]
      (dom/div #js {:className "login-page"
                    :onKeyUp #(when (= 13 (-> % .-keyCode))
                                (.onLogin this))}
               (dom/h1 #js {:className "logo"} "Masters of Cthulhu")
               (box {:title "Login"
                     :footer (.footer this)}
                    (icon-input (om/computed {:icon "user"
                                              :auto-focus true
                                              :placeholder "Email"
                                              :value email
                                              :error (:email errors)}
                                             {:on-update #(.onEmailUpdate this %)}))
                    (icon-input (om/computed {:icon "lock"
                                              :type "password"
                                              :placeholder "Password"
                                              :value password
                                              :error (:password errors)}
                                             {:on-update #(.onPasswordUpdate this %)}))
                    (button (om/computed {} {:on-click #(.onLogin this)})
                            "Log in"))))))

(def login (om/factory Login))
