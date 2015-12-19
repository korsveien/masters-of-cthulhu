(ns moc.ui.common.input
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui Input
  Object
  (render [this]
    (let [{:keys [type auto-focus placeholder]} (om/props this)]
      (dom/div #js {:className "input-box"}
               (dom/input #js {:type (or type "text")
                               :autoFocus (or auto-focus false)
                               :placeholder placeholder})))))

(def input (om/factory Input))
