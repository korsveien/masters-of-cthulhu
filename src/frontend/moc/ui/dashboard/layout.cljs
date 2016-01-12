(ns moc.ui.dashboard.layout
  (:require [moc.ui.common.sidebar-layout :refer [sidebar-layout]]
            [moc.ui.common.link-list :refer [link-list]]))

(defn layout [opts & children]
  (into [sidebar-layout {:sidebar {:dropdown [{:title "Dashboard"
                                               :icon "dashboard"
                                               :href "test"}]
                                   :content [link-list
                                             {:text "Games"
                                              :href [:url/index]}
                                             {:text "Profile"
                                              :href [:url.user/profile]}
                                             {:text "Password"
                                              :href [:url.user/password]}
                                             {:text "Log out"
                                              :href [:url.user/logout]}]}
                         :content {:header (:header opts)}}]
        children))
