(ns moc.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [goog.dom :as gdom]
            [reagent.core :as reagent]
            [bidi.bidi :as bidi]
            [moc.urls :refer [urls]])
  (:import goog.history.Html5History))

(defmulti route->component :handler)

(defn route-to-current-path []
  (let [bidi-info (bidi/match-route urls js/window.location.pathname)
        component (route->component bidi-info)]
    (reagent/render [component bidi-info]
                    (gdom/getElement "app"))))

(defonce history
  (doto (Html5History.)
    (events/listen EventType/NAVIGATE route-to-current-path)
    (.setPathPrefix (str js/window.location.protocol
                         "//"
                         js/window.location.host))
    (.setUseFragment false)))

(defn navigate! [path]
  (when-not (.-enabled_ history)
    (.setEnabled history))

  (if path
    (.setToken history (apply bidi/path-for urls path))
    (route-to-current-path)))
