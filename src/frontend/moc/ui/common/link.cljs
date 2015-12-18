(ns moc.ui.common.link
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [bidi.bidi :as bidi]
            [moc.urls :refer [urls]]
            [moc.router :refer [navigate!]]))

(defui Link
  Object
  (render [this]
    (let [{:keys [path]} (om/props this)
          url (apply bidi/path-for urls path)]
      (dom/a #js {:href "#"
                  :onClick (fn [e]
                             (.preventDefault e)
                             (navigate! url))}
             (om/children this)))))

(def link (om/factory Link))
