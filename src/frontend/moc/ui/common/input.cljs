(ns moc.ui.common.input
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui Input
  Object
  (render [this]
    (let [{:keys [icon type placeholder]} (om/props this)]
      (dom/div #js {:className "input-box"}
               (when icon
                 (dom/i #js {:className (str "fa fa-" icon)}))
               (dom/input #js {:type (or type "text")
                               :placeholder placeholder})))))

(def input (om/factory Input))
