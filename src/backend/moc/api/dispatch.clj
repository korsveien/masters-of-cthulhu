(ns moc.api.dispatch
  (:require [om.next.server :as om]))

(defmulti reader (fn [_ k _] k))


(defmulti mutator (fn [_ k _] k))


(def parser
  (om/parser {:read reader
              :mutate mutator}))
