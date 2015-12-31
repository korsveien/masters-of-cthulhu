(ns moc.ajax
  (:refer-clojure :exclude [get])
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

(def error-status? #{400 404 500})

(defn default-error-handler! []
  (js/alert "Something went wrong. Try again later."))

(defn request [{:keys [path data on-success on-fail]}]
  (let [url (apply bidi/path-for urls path)]
    (.send XhrIo url
           (fn [e]
             (let [xhr (.-target e)
                   status-code (.getStatus xhr)
                   response (try
                              (transit/read (reader) (.getResponseText xhr))
                              (catch js/Error e
                                nil))]
               (cond (error-status? status-code)
                     (if on-fail
                       (on-fail response)
                       (default-error-handler!))

                     (= 401 status-code)
                     (js/alert "Unauthenticated!")

                     on-success
                     (on-success response))))
           "PUT" (transit/write (writer) data)
           #js {"Content-Type" "application/transit+json; charset=UTF-8"})))
