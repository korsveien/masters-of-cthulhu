(ns moc.reader.errors
  (:require [moc.reader.dispatch :refer [reader]]))

(defmethod reader :errors [{:keys [state]} _ _]
  {:value (:errors @state)})
