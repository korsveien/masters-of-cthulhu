(ns moc.ui.not-found
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui NotFound
  Object
  (render [this]
    (dom/div nil "Page not found!")))

(def not-found (om/factory NotFound))
