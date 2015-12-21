(ns moc.ui.authentication.register
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
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
  (render [this]
    (dom/div #js {:className "register-page"}
             (dom/h1 #js {:className "logo"} "Masters of Cthulhu")
             (box {:title "Register"
                   :footer (footer)}
                  (icon-input {:icon "user"
                               :auto-focus true
                               :placeholder "Email"})
                  (icon-input {:icon "lock"
                               :type "password"
                               :placeholder "Password"})
                  (icon-input {:icon "lock"
                               :type "password"
                               :placeholder "Confirm password"})
                  (button nil "Register")))))

(def register (om/factory Register))
