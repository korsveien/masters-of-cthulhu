(ns moc.ui.router
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.ui.authentication.login :as login]
            [moc.ui.authentication.register :as register]
            [moc.ui.not-found :as not-found]))

(defn url->component-pair [url])

(defui Router
  static om/IQuery
  (query [this]
    '[:url])
  Object
  (render [this]
    (let [props (om/props this)]
      (dom/div #js {} "HELLO WORLD!"))))

