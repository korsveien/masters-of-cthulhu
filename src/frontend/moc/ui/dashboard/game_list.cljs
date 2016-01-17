(ns moc.ui.dashboard.game-list
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.common.link :refer [link]]))

(defn game-list-header []
  [:span
   [:span.title "Games"]
   [:span.right [link {:path [:url.dashboard/new-game]} [:i.fa.fa-plus]]]])

(defn game-list [_]
  (let []
    (fn [_]
      [:div "game list"])))
