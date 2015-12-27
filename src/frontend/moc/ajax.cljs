(ns moc.ajax
  (:require [cognitect.transit :as transit]
            [cljs-time.coerce :as time.coerce]
            [bidi.bidi :as bidi]
            [moc.urls :refer [urls]])
  (:import [goog.net XhrIo]
           [goog.date UtcDateTime]))

(deftype DateHandler []
  Object
  (tag [_ v] "ct")
  (rep [_ v] (time.coerce/to-long v))
  (stringRep [_ v] (time.coerce/to-long v)))

(def read-opts {:handlers {"ct" #(time.coerce/from-long (js/parseInt %))}})
(def write-opts {:handlers {goog.date.UtcDateTime (DateHandler.)}})

(defn reader []
  (transit/reader :json read-opts))

(defn writer []
  (transit/writer :json write-opts))

(defn transit-post [edn cb]
  (let [url (bidi/path-for urls :api)]
    (.send XhrIo url
           (fn [e]
             (this-as this
                      (cb (transit/read (reader) (.getResponseText this)))))
           "POST" (transit/write (writer) edn)
           #js {"Content-Type" "application/transit+json"})))
