(ns moc.ui.dashboard.password
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.dashboard.layout :refer [layout]]
            [moc.ui.common.input :refer [input]]
            [moc.ui.common.button :refer [button]]))

(defn password [_]
  (let [loading? (subscribe [:loading?])]
    (fn [_]
      [layout {:header [:h3.title "Change password"]}
       [input {:label "Password"
               :type "password"
               :auto-focus true}]
       [input {:label "Confirm password"
               :type "password"}]
       [button {:loading? @loading?}
        "Save"]])))
