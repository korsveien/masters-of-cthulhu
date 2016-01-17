(ns moc.ui.router
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.authentication.login :refer [login]]
            [moc.ui.authentication.register :refer [register]]
            [moc.ui.dashboard.layout :as dashboard]
            [moc.ui.not-found :refer [not-found]]))

(defn route->component [{:keys [handler]}]
  (let [root (namespace handler)]
    (cond (or (= handler :url/index) (= root "url.user"))
          dashboard/layout

          (= handler :url.auth/login)
          login

          (= handler :url.auth/register)
          register

          :else
          not-found)))

(defn loading-page [visible?]
  [:div.loading-page {:class (if visible? "visible")}
   [:h2 "Loading"]
   [:i.fa.fa-spin.fa-spinner.fa-2x]])

(defn router []
  (let [loading? (subscribe [:loading?])
        user (subscribe [:user/current])
        route-info (subscribe [:route/info])]
    (fn []
      (let [component (route->component @route-info)]
        [:div#app-wrapper
         [loading-page (and (nil? @user) @loading?)]
         [component @route-info]]))))
