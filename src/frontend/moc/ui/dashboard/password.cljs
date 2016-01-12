(ns moc.ui.dashboard.password
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.dashboard.layout :refer [layout]]))

(defn password [_]
  (let []
    (fn [_]
      [layout {:header [:span.title "Password"]}
       [:div "password"]])))
