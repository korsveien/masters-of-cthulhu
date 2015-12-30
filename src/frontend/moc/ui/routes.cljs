(ns moc.ui.routes
  (:require [moc.router :refer [route->component]]
            [moc.ui.authentication.login :refer [login]]
            [moc.ui.authentication.register :refer [register]]
            [moc.ui.not-found :refer [not-found]]))

(defmethod route->component :url.user/login [_]
  login)

(defmethod route->component :url.user/register [_]
  register)

(defmethod route->component :default [_]
  not-found)
