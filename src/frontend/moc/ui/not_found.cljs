(ns moc.ui.not-found
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.router :refer [router]]))

(defui NotFound
  Object
  (render [this]
    (dom/div nil "Page not found!")))

(def not-found (om/factory NotFound))

(defmethod router :default [opts]
  (js/ReactDOM.render (not-found) (gdom/getElement "app")))
