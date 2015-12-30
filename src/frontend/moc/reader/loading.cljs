(ns moc.reader.loading
  (:require [moc.reader.dispatch :refer [reader]]))

(defmethod reader :loading? [{:keys [state]} _ _]
  (let [v (:loading? @state)]
    (if v
      {:value v :remote true}
      {:value v})))
