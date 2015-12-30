(ns moc.ui.common.link
  (:require [bidi.bidi :as bidi]
            [moc.urls :refer [urls]]
            [moc.router :refer [navigate!]]))

(defn link [{:keys [path]} & children]
  [:a {:href (apply bidi/path-for urls path)
       :on-click (fn [e]
                   (.preventDefault e)
                   (navigate! path))}
   children])
