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

(def error-status? #{400 404 500})

(defn transit-post [{:keys [remote]} cb]
  (let [url (bidi/path-for urls :api)]
    (.send XhrIo url
           (fn [e]
             (let [xhr (.-target e)
                   status-code (.getStatus xhr)]
               (cond (error-status? status-code)
                     (js/alert "Something went wrong. Try again later.")

                     (= 401 status-code)
                     (js/alert "Unauthenticated!")

                     :else
                     (cb (transit/read (reader) (.getResponseText xhr))))))
           "POST" (transit/write (writer) remote)
           #js {"Content-Type" "application/transit+json"})))
