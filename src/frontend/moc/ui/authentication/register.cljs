(ns moc.ui.authentication.register
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as user]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]))

(defn footer []
  (dom/span nil
            "Are you a user? "
            (link {:path [:user/login]} "Log in")))

(defui Register
  Object
  (onEmailUpdate [this value]
    (om/update-state! this assoc :email value))

  (onPasswordUpdate [this value]
    (om/update-state! this assoc :password value))

  (onConfPassUpdate [this value]
    (om/update-state! this assoc :confirm-password value))

  (onSubmit [this]
    (if-let [errors (user/validate-register-schema (om/get-state this))]
      (om/update-state! this assoc :errors errors)
      (do
        (om/update-state! this assoc :errors {})
        (println "valid"))))

  (render [this]
    (let [{:keys [email password confirm-password errors]} (om/get-state this)]
      (dom/div #js {:className "register-page"
                    :onKeyUp #(when (= 13 (-> % .-keyCode))
                                (.onSubmit this))}
               (dom/h1 #js {:className "logo"} "Masters of Cthulhu")
               (box {:title "Register"
                     :footer (footer)}
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
                    (icon-input (om/computed {:icon "lock"
                                              :type "password"
                                              :placeholder "Confirm password"
                                              :value confirm-password
                                              :error (:confirm-password errors)}
                                             {:on-update #(.onConfPassUpdate this %)}))
                    (button (om/computed {} {:on-click #(.onSubmit this)})
                            "Register"))))))

(def register (om/factory Register))
