(ns moc.ui.router
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.authentication.login :refer [login]]
            [moc.ui.authentication.register :refer [register]]
            [moc.ui.not-found :refer [not-found]]))

(defn route->component [route-info]
  (case (:handler route-info)
    :url.user/login login
    :url.user/register register
    not-found))

(defn router []
  (let [loading? (subscribe [:loading?])
        user (subscribe [:user/current])
        route-info (subscribe [:route/info])]
    (fn []
      (if (or (and (nil? @user) @loading?))
        [:div "LOADING"]
        (let [component (route->component @route-info)]
          [component @route-info])))))