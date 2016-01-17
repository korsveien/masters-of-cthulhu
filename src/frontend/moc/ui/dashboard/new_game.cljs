(ns moc.ui.dashboard.new-game
  (:require [re-frame.core :refer [subscribe]]))

(defn new-game-header []
  [:span.title "New game"])

(defn new-game [_]
  (let []
    (fn [_]
      [:div "new-game"])))
