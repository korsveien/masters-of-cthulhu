(ns moc.core
  (:require [cljsjs.fastclick]
            [om.next :as om]
            [goog.dom :as gdom]
            [moc.router :as router]
            [moc.ui.router :refer [Router]]
            [moc.reader.dispatch :refer [reader]]
            [moc.mutator.dispatch :refer [mutator]]
            [moc.reader.imports]
            [moc.mutator.imports]))

(defonce app-state (atom {:url {:handler :index}}))

(defonce reconciler (om/reconciler {:state app-state
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
