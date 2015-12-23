(ns moc.reader.url
  (:require [moc.reader.dispatch :refer [reader]]))

(defmethod reader :url [{:keys [state]} _ _]
  {:value (:url @state)})
