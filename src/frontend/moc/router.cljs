(ns moc.router
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [bidi.bidi :as bidi]
            [om.next :as om]
            [moc.urls :refer [urls]])
  (:import goog.history.Html5History))

(defonce history
  (doto (Html5History.)
    (.setPathPrefix (str js/window.location.protocol
                         "//"
                         js/window.location.host))
    (.setUseFragment false)
    (.setEnabled true)))

(defn route-to-current-path [reconciler]
  (let [bidi-info (bidi/match-route urls js/window.location.pathname)]
    (om/transact! reconciler `[(url/set ~bidi-info)])))

(defn navigate! [reconciler path]
  (when path
    (.setToken history (apply bidi/path-for urls path)))
  (route-to-current-path reconciler))
