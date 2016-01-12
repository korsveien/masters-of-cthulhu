(ns moc.ui.dashboard.profile
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.dashboard.layout :refer [layout]]))

(defn profile [_]
  (let []
    (fn [_]
      [layout {:header [:span.title "Profile"]}
       [:div "profile"]])))
