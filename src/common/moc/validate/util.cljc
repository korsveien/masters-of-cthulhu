(ns moc.validate.util
  (:require [bouncer.core :as bouncer]))

(defn- format-errors [errors]
  (when errors
    (-> (fn [acc k v]
          (assoc acc k (first v)))
        (reduce-kv {} errors))))

(defn validate [item schema]
  (-> (bouncer/validate item schema)
      second
      :bouncer.core/errors
      format-errors))
