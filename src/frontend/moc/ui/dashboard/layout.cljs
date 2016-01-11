(ns moc.ui.dashboard.layout
  (:require [moc.ui.common.sidebar-layout :refer [sidebar-layout]]))

(defn layout [& children]
  (into [sidebar-layout {:sidebar {:dropdown [{:title "Dashboard"
                                               :icon "dashboard"
                                               :href "test"}]
                                   :content [:div
                                             "Games"
                                             "Profile"
                                             "Password"
                                             "Log out"]
                                   :footer [:div "Footer"]}}]
        children))
