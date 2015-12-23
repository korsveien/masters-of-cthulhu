(ns moc.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [bidi.bidi :as bidi]
            [om.next :as om]
            [moc.urls :refer [urls]])
  (:import goog.history.Html5History))

(defonce reconciler nil)

(defn set-reconciler! [rec]
  (set! reconciler rec))

(defn route-to-current-path []
  (when reconciler
    (let [bidi-info (bidi/match-route urls js/window.location.pathname)]
      (om/transact! reconciler `[(url/set ~bidi-info)]))))

(defonce history
  (doto (Html5History.)
    (events/listen EventType/NAVIGATE route-to-current-path)
    (.setPathPrefix (str js/window.location.protocol
                         "//"
                         js/window.location.host))
    (.setUseFragment false)
    (.setEnabled true)))

(defn navigate! [path]
  (if path
    (.setToken history (apply bidi/path-for urls path))
    (route-to-current-path)))
