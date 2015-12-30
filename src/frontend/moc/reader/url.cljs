(ns moc.reader.url
  (:require [moc.reader.dispatch :refer [reader sub-remote?]]))

(defmethod reader :url [{:keys [state]} _ _]
  {:value (:url @state)})

(defmethod reader :page-query [{:keys [query parser] :as env} _ _]
  {:remote (sub-remote? env query)
   :value (parser env query)})
