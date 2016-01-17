(ns moc.ui.dashboard.game-list
  (:require [re-frame.core :refer [subscribe]]))

(defn game-list-header []
  [:span
   [:span.title "Games"]
   [:i.fa.fa-plus]])

(defn game-list [_]
  (let []
    (fn [_]
      [:div "game list"])))
