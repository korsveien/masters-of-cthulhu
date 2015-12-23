(ns moc.reader.dispatch
  (:require [om.next :as om]))

(defmulti reader om/dispatch)
