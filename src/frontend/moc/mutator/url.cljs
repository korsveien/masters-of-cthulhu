(ns moc.mutator.url
  (:require [moc.mutator.dispatch :refer [mutator]]))

(defmethod mutator 'url/set [{:keys [state]} _ params]
  {:value {:keys [:url]}
   :action #(swap! state assoc :url params)})
