(ns moc.reader.loading
  (:require [moc.reader.dispatch :refer [reader]]))

(defmethod reader :loading? [{:keys [state]} _ _]
  {:value (:loading? @state)})
