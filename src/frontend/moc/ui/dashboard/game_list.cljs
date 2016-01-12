(ns moc.ui.dashboard.game-list
  (:require [re-frame.core :refer [subscribe]]
            [moc.ui.dashboard.layout :refer [layout]]))

(defn game-list [_]
  (let []
    (fn [_]
      [layout {:header [:span
                        [:span.title "Games"]
                        [:i.fa.fa-plus]]}
       [:div "game list"]])))
