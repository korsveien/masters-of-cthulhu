(ns moc.util
  (:require [clj-time.format :as time.format]
            [cemerick.url :refer [url]]))

(defn base-url [full-url]
  (-> (url full-url)
      (assoc :path nil)
      (str)))

(defn join-urls [base path]
  (url base path))

(defn date-time-format [time]
  (time.format/unparse (time.format/formatters :rfc822)
                       time))
