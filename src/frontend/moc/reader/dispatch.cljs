(ns moc.reader.dispatch
  (:require [om.next :as om]))

(defmulti reader om/dispatch)

(defn sub-remote? [env query]
  (->> query
       (map #(reader env % nil))
       (map :remote)
       (some true?)))
