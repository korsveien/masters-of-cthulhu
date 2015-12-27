(ns moc.core
  (:require [cljsjs.fastclick]
            [om.next :as om]
            [goog.dom :as gdom]
            [moc.ajax :as ajax]
            [moc.router :as router]
            [moc.ui.router :refer [Router]]
            [moc.reader.dispatch :refer [reader]]
            [moc.mutator.dispatch :refer [mutator]]
            [moc.reader.imports]
            [moc.mutator.imports]))

(def app-state {:url {:handler :index}
                :errors {}
                :loading? false
                :user/current nil})

(defonce reconciler (om/reconciler {:state app-state
                                    :send ajax/transit-post
                                    :parser (om/parser {:read reader
                                                        :mutate mutator})}))


(defn ^:export reload! []
  (router/navigate! nil))

(defn ^:export main []
  (enable-console-print!)
  (.attach js/FastClick js/document.body)
  (router/set-reconciler! reconciler)
  (om/add-root! reconciler Router (gdom/getElement "app"))
  (reload!))
