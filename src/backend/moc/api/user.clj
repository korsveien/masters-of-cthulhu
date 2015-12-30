(ns moc.api.user
  (:require [moc.api.dispatch :refer [mutator]]))

(defmethod mutator 'user/register [_ _ _]
  {:value nil})

(defmethod mutator 'user/login [_ _ _]
  {:value nil})
