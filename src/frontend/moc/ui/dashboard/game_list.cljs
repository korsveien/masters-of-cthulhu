(ns moc.ui.dashboard.game-list
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.dashboard.layout :refer [layout]]))

(defn game-list [_]
  (let []
    (fn [_]
      [layout
       [:div "game list"]])))
