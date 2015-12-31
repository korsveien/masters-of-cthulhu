(ns moc.core
  (:require [moc.router :as router]
            [moc.ui.routes]))

(defn ^:export reload! []
  (router/navigate! nil))

(defn ^:export main []
  (enable-console-print!)
  (reload!))
