(ns moc.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [bidi.bidi :as bidi]
            [moc.urls :refer [urls]])
  (:import goog.history.Html5History))

(defmulti router :handler)

(defn get-path []
  (bidi/match-route urls js/window.location.pathname))

(defonce history
  (doto (Html5History.)
    (events/listen EventType/NAVIGATE #(router (get-path)))
    (.setPathPrefix (str js/window.location.protocol
                         "//"
                         js/window.location.host))
    (.setUseFragment false)))

(defn activate! []
  (.setEnabled history true)
  (router (get-path)))

(defn navigate! [to]
  (.setToken history to))
