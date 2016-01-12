(ns moc.ui.router
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.authentication.login :refer [login]]
            [moc.ui.authentication.register :refer [register]]
            [moc.ui.dashboard.game-list :refer [game-list]]
            [moc.ui.dashboard.new-game :refer [new-game]]
            [moc.ui.dashboard.profile :refer [profile]]
            [moc.ui.dashboard.password :refer [password]]
            [moc.ui.not-found :refer [not-found]]))

(defn route->component [route-info]
  (case (:handler route-info)
    :url/index game-list
    :url.user/login login
    :url.user/register register
    :url.user/profile profile
    :url.user/password password
    :url.game/new new-game
    not-found))

(defn loading-page []
  [:div.loading-page
   [:h2 "Loading"]
   [:i.fa.fa-spin.fa-spinner.fa-2x]])

(defn router []
  (let [loading? (subscribe [:loading?])
        user (subscribe [:user/current])
        route-info (subscribe [:route/info])]
    (fn []
      (if (or (and (nil? @user) @loading?))
        [loading-page]
        (let [component (route->component @route-info)]
          [component @route-info])))))
