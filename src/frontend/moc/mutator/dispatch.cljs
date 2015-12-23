(ns moc.mutator.dispatch
  (:require [om.next :as om]))

(defmulti mutator om/dispatch)
