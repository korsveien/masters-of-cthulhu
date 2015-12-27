(ns moc.mutator.errors
  (:require [moc.mutator.dispatch :refer [mutator]]))

(defmethod mutator 'errors/set [{:keys [state]} _ params]
  {:value {:keys [:errors]}
   :action #(swap! state assoc :errors params)})

(defmethod mutator 'errors/clear [{:keys [state]} _ _]
  {:value {:keys [:errors]}
   :action #(swap! state assoc :errors {})})
