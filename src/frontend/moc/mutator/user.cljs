(ns moc.mutator.user
  (:require [moc.mutator.dispatch :refer [mutator]]))

(defmethod mutator 'user/login [_ _ _]
  {:remote true})

(defmethod mutator 'user/register [_ _ _]
  {:remote true})
