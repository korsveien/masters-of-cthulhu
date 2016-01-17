(ns moc.ui.common.link-list
  (:require [moc.ui.common.link :refer [link]]))

(defn link-list [current-path & elements]
  (into [:ul.link-list]
        (for [{:keys [text href dispatch]} elements]
          [:li {:class (if (= current-path (first href)) "active")}
           [link {:path href
                  :dispatch dispatch}
            text]])))
