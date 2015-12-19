(ns moc.ui.authentication.login
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.router :refer [router]]
            [moc.util :as util]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.icon-input :refer [icon-input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]))

(defn footer []
  (dom/span nil
            "Not a user? "
            (link {:path [:user/register]} "Sign up")))

(defui Login
  Object
  (render [this]
    (dom/div nil
             (dom/h1 #js {:className "logo"} "Masters of Cthulhu")
             (box {:title "Login"
                   :footer (footer)}
                  (icon-input {:icon "user"
                               :auto-focus true
                               :placeholder "Email"})
                  (icon-input {:icon "lock"
                               :type "password"
                               :placeholder "Password"})
                  (button nil "Log in")))))

(def login (om/factory Login))

(defmethod router :user/login [_]
  (util/render (login)))
