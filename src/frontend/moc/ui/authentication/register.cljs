(ns moc.ui.authentication.register
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.router :refer [router]]
            [moc.util :as util]))

(defui Register
  Object
  (render [this]
    (dom/div nil "Register")))

(def register (om/factory Register))

(defmethod router :user/register [_]
  (util/render (register)))
