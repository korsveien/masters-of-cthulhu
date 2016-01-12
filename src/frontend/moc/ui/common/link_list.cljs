(ns moc.ui.common.link-list
  (:require [moc.ui.common.link :refer [link]]))

(defn link-list [& elements]
  (into [:ul.link-list]
        (for [{:keys [text href]} elements]
          [:li
           [link {:path href} text]])))
