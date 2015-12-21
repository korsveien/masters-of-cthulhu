(ns moc.ui.authentication.routes
  (:require [moc.router :refer [router]]
            [moc.util :as util]
            [moc.ui.authentication.login :refer [login]]
            [moc.ui.authentication.register :refer [register]]))

(defmethod router :user/login [_]
  (util/render (login)))

(defmethod router :user/register [_]
  (util/render (register)))
