(ns moc.transit
  (:refer-clojure :exclude [read])
  (:require [cognitect.transit :as transit]
            [clj-time.coerce :as time.coerce])
  (:import java.io.ByteArrayOutputStream
           org.joda.time.DateTime))

(def time-writer (transit/write-handler
                  (constantly "ct")
                  time.coerce/to-long
                  #(.toString (time.coerce/to-long %))))

(def time-reader (transit/read-handler #(-> (Long/parseLong %)
                                            (time.coerce/from-long))))

(def write-opts {:handlers {org.joda.time.DateTime time-writer}})
(def read-opts {:handlers {"ct" time-reader}})


(defn write [content]
  (let [stream (ByteArrayOutputStream.)
        w (transit/writer stream :json write-opts)]
    (transit/write w content)
    (.toString stream "UTF-8")))

(defn read [body]
  (when body
    (let [reader (transit/reader body :json read-opts)]
      (transit/read reader))))
