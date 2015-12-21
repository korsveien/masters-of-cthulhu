(ns moc.ui.routes
  (:require [moc.router :refer [router]]
            [moc.util :as util]
            [moc.ui.not-found :refer [not-found]]
            [moc.ui.authentication.routes]))

(defmethod router :default [_]
  (util/render (not-found)))
