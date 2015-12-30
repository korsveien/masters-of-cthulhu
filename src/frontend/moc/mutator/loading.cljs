(ns moc.mutator.loading
  (:require [moc.mutator.dispatch :refer [mutator]]))

(defmethod mutator 'loading/set [{:keys [state]} _ _]
  {:value {:keys [:loading?]}
   :action #(swap! state assoc :loading? true)})
