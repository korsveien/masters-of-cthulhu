(ns moc.ui.dashboard.new-game
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.dashboard.layout :refer [layout]]))

(defn new-game [_]
  (let []
    (fn [_]
      [layout {:header [:span.title "New game"]}
       [:div "new-game"]])))
