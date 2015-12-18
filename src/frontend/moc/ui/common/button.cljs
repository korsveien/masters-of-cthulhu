(ns moc.ui.common.button
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui Button
  Object
  (render [this]
    (let [{:keys [title]} (om/props this)]
      (dom/a #js {} (om/children this)))))

(def button (om/factory Button))
