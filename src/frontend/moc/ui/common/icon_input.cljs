(ns moc.ui.common.icon-input
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui IconInput
  Object
  (render [this]
    (let [{:keys [icon type auto-focus placeholder]} (om/props this)]
      (dom/div #js {:className "icon-input-box"}
               (dom/div #js {:className "icon"}
                        (dom/i #js {:className (str "fa fa-" icon)}))
               (dom/input #js {:type (or type "text")
                               :autoFocus (or auto-focus false)
                               :placeholder placeholder})))))

(def icon-input (om/factory IconInput))
