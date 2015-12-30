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

  (onLogin [this]
    (let [state (om/get-state this)
          errors (validate state user/login-schema)]
      (if errors
        (om/transact! this `[(errors/set ~errors)])
        (om/transact! this `[(errors/clear)
                             (loading/set)
                             (user/login ~state)
                             :errors
                             :loading?]))))

  (footer [_]
    (dom/span nil
              "Not a user? "
              (link {:path [:user/register]} "Sign up")))

  (render [this]
    (let [{:keys [errors loading?]} (om/props this)
          {:keys [email password]} (om/get-state this)]
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
                    (button (om/computed {:loading? loading?}
                                         {:on-click #(.onLogin this)})
                            "Log in"))))))

(def login (om/factory Login))
