(ns moc.ui.authentication.login
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.router :refer [router]]
            [moc.util :as util]))

(defui Login
  Object
  (render [this]
    (dom/div nil "Login")))

(def login (om/factory Login))

(defmethod router :user/login [_]
  (util/render (login)))
