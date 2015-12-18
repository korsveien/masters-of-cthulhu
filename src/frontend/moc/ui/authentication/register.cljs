(ns moc.ui.authentication.register
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.router :refer [router]]
            [moc.util :as util]
            [moc.ui.common.box :refer [box]]
            [moc.ui.common.input :refer [input]]
            [moc.ui.common.button :refer [button]]
            [moc.ui.common.link :refer [link]]))

(defn footer []
  (dom/span nil
            "Are you a user? "
            (link {:path [:user/login]} "Log in")))

(defui Register
  Object
  (render [this]
    (dom/div nil
             (dom/h1 #js {:className "logo"} "Masters of Cthulhu")
             (box {:title "Register"
                   :footer (footer)}
                  (input {:icon "user"
                          :placeholder "Email"})
                  (input {:icon "lock"
                          :type "password"
                          :placeholder "Password"})
                  (input {:icon "lock"
                          :type "password"
                          :placeholder "Confirm password"})
                  (button nil "Register")))))

(def register (om/factory Register))

(defmethod router :user/register [_]
  (util/render (register)))
