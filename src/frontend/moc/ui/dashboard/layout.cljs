(ns moc.ui.dashboard.layout
  (:require [moc.ui.common.sidebar-layout :refer [sidebar-layout]]
            [moc.ui.common.link-list :refer [link-list]]
            [moc.ui.dashboard.game-list :refer [game-list game-list-header]]
            [moc.ui.dashboard.new-game :refer [new-game new-game-header]]
            [moc.ui.dashboard.profile :refer [profile profile-header]]))

(defn- router [handler]
  (case handler
    :url.dashboard/index [game-list game-list-header]
    :url.dashboard/profile [profile profile-header]
    :url.dashboard/new-game [new-game new-game-header]))

(defn layout [{:keys [handler]}]
  (let [component (router handler)]
    [sidebar-layout {:sidebar {:dropdown [{:title "Dashboard"
                                           :icon "dashboard"
                                           :href [:url.dashboard/index]}]
                               :content [link-list handler
                                         {:text "Games"
                                          :href [:url.dashboard/index]}
                                         {:text "Profile"
                                          :href [:url.dashboard/profile]}
                                         {:text "Log out"
                                          :dispatch [:user/logout]}]}
                     :content {:header [(second component)]}}
     [(first component)]]))
