(ns moc.ui.dashboard.profile
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.common.input :refer [input]]
            [moc.ui.common.button :refer [button]]))

(defn profile-header []
  [:span.title "Profile"])

(defn profile [_]
  (let [user (subscribe [:user/current])]
    (fn [_]
      [:div
       [input {:label "Name"
               :auto-focus true
               :value (:name @user)}]
       [input {:label "Email"
               :value (:email @user)}]
       [button {} "Save"]])))
