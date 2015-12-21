(ns moc.core
  (:require [cljsjs.fastclick]
            [moc.router :as router]
            [moc.ui.routes]))

(defn ^:export reload! []
  (router/activate!))

(defn ^:export main []
  (enable-console-print!)
  (.attach js/FastClick js/document.body)
  (reload!))
