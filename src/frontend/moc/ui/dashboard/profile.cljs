(ns moc.ui.dashboard.profile
  (:require [re-frame.core :refer [subscribe]]))

(defn profile-header []
  [:span.title "Profile"])

(defn profile [_]
  (let []
    (fn [_]
      [:div "profile"])))
