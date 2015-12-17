(ns moc.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [bidi.bidi :as bidi]
            [moc.urls :refer [urls]])
  (:import goog.history.Html5History))

(defmulti router :handler)

(defn activate! []
  (let [h (Html5History.)
        cb (fn [e]
             (let [result (bidi/match-route urls (.-token e))]
               (router result)))]
    (events/listen h EventType/NAVIGATE cb)
    (.setEnabled h true)))
