(ns moc.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [re-frame.core :refer [dispatch]]
            [bidi.bidi :as bidi]
            [moc.urls :refer [urls]])
  (:import goog.history.Html5History))

(defn route-to-current-path []
  (let [bidi-info (bidi/match-route urls js/window.location.pathname)]
    (dispatch [:route/set-info bidi-info])))

(defonce history
  (doto (Html5History.)
    (events/listen EventType/NAVIGATE route-to-current-path)
    (.setPathPrefix (str js/window.location.protocol
                         "//"
                         js/window.location.host))
    (.setUseFragment false)))

(defn navigate! [path]
  (if-not (.-enabled_ history)
    (.setEnabled history true)
    (if path
      (.setToken history (apply bidi/path-for urls path))
      (route-to-current-path))))
