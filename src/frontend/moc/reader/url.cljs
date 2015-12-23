(ns moc.reader.url
  (:require [moc.reader.dispatch :refer [reader]]))

(defmethod reader :url [{:keys [state]} _ _]
  {:value (:url @state)})

(defmethod reader :page-query [{:keys [query parser] :as env} _ _]
  {:value (parser env query)})
