(ns moc.core
  (:require [cljsjs.fastclick]
            [moc.router :as router]
            [moc.ui.authentication.login]
            [moc.ui.authentication.register]
            [moc.ui.not-found]))

(defn ^:export reload! []
  (router/activate!))

(defn ^:export main []
  (enable-console-print!)
  (.attach js/FastClick js/document.body)
  (reload!))
