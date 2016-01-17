(ns moc.ui.dashboard.layout
  (:require [moc.ui.common.sidebar-layout :refer [sidebar-layout]]
            [moc.ui.common.link-list :refer [link-list]]
            [moc.ui.dashboard.game-list :refer [game-list game-list-header]]
            [moc.ui.dashboard.new-game :refer [new-game new-game-header]]
            [moc.ui.dashboard.profile :refer [profile profile-header]]
            [moc.ui.dashboard.password :refer [password password-header]]))

(defn- router [handler]
  (case handler
    :url/index [game-list game-list-header]
    :url.user/profile [profile profile-header]
    :url.user/password [password password-header]
    :url.game/new [new-game new-game-header]))

(defn layout [{:keys [handler]}]
  [sidebar-layout {:sidebar {:dropdown [{:title "Dashboard"
                                         :icon "dashboard"
                                         :href "test"}]
                             :content [link-list handler
                                       {:text "Games"
                                        :href [:url/index]}
                                       {:text "Profile"
                                        :href [:url.user/profile]}
                                       {:text "Password"
                                        :href [:url.user/password]}
                                       {:text "Log out"
                                        :href [:url.auth/logout]}]}
                   :content {:header [(second (router handler))]}}
   [(first (router handler))]])
