(ns moc.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [bidi.bidi :as bidi]
            [moc.urls :refer [urls]])
  (:import goog.history.Html5History))

(defmulti router :handler)

(defn activate! []
  (let [cb (fn [e]
             (let [path js/window.location.pathname
                   result (bidi/match-route urls path)]
               (router result)))]
    (doto (Html5History.)
      (events/listen EventType/NAVIGATE cb)
      (.setEnabled true))))
