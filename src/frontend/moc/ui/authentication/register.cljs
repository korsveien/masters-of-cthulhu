(ns moc.ui.authentication.register
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as user]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]))

(defui Register
  static om/IQuery
  (query [_]
    '[:errors :loading?])

  Object
  (componentWillUnmount [this]
    (om/transact! this `[(errors/clear)]))

  (onEmailUpdate [this value]
    (om/update-state! this assoc :email value))

  (onPasswordUpdate [this value]
    (om/update-state! this assoc :password value))

  (onConfPassUpdate [this value]
    (om/update-state! this assoc :confirm-password value))

  (onSubmit [this]
    (let [state (om/get-state this)
          errors (user/validate-register-schema state)]
      (if errors
        (om/transact! this `[(errors/set ~errors)])
        (om/transact! this `[(errors/clear)
                             (loading/set)
                             (user/register ~state)]))))

  (footer [_]
    (dom/span nil
              "Are you a user? "
              (link {:path [:user/login]} "Log in")))

  (render [this]
    (let [{:keys [errors loading?]} (om/props this)
          {:keys [email password confirm-password]} (om/get-state this)]
      (dom/div #js {:className "register-page"
                    :onKeyUp #(when (= 13 (-> % .-keyCode))
                                (.onSubmit this))}
               (dom/h1 #js {:className "logo"} "Masters of Cthulhu")
               (box {:title "Register"
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
                    (icon-input (om/computed {:icon "lock"
                                              :type "password"
                                              :placeholder "Confirm password"
                                              :value confirm-password
                                              :error (:confirm-password errors)}
                                             {:on-update #(.onConfPassUpdate this %)}))
                    (button (om/computed {:loading? loading?}
                                         {:on-click #(.onSubmit this)})
                            "Register"))))))

(def register (om/factory Register))
