(ns moc.ui.common.button
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui Button
  Object
  (render [this]
    (let [{:keys [on-click]} (om/get-computed this)]
      (dom/a #js {:href "#"
                  :className "button"
                  :onClick #(and on-click (on-click))}
             (om/children this)))))

(def button (om/factory Button))
