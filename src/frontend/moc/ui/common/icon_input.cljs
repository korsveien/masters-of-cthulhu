(ns moc.ui.common.icon-input
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui IconInput
  Object
  (onChange [this e]
    (let [{:keys [on-update]} (om/get-computed this)]
      (when on-update
        (on-update (-> e .-target .-value)))))

  (render [this]
    (let [{:keys [icon type auto-focus placeholder value error]} (om/props this)]
      (dom/div #js {:className "icon-input-box"}
               (dom/div #js {:className "icon"}
                        (dom/i #js {:className (str "fa fa-" icon)}))
               (dom/input #js {:type (or type "text")
                               :autoFocus (or auto-focus false)
                               :placeholder placeholder
                               :value value
                               :onChange #(.onChange this %)})
               (when error
                 (dom/div #js {:className "error"}
                          (dom/i #js {:className "fa fa-warning"})
                          (dom/span #js {:className "text"} error)))))))

(def icon-input (om/factory IconInput))
