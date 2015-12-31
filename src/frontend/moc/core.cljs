(ns moc.core
  (:require [re-frame.core :refer [dispatch-sync]]
            [moc.db]
            [moc.router :as router]
            [moc.ui.routes]
            [moc.subscription.imports]
            [moc.handler.imports]))

(defn ^:export reload! []
  (router/navigate! nil))

(defn ^:export main []
  (enable-console-print!)
  (dispatch-sync [:app/initialize])
  (reload!))
