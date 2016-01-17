(ns moc.ui.dashboard.password
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.common.input :refer [input]]
            [moc.ui.common.button :refer [button]]))

(defn password-header []
  [:span.title "Change password"])

(defn password [_]
  (let [loading? (subscribe [:loading?])]
    (fn [_]
      [:div
       [input {:label "New password"
               :type "password"
               :auto-focus true}]
       [input {:label "Confirm password"
               :type "password"}]
       [button {:loading? @loading?}
        "Save"]])))
