(ns moc.ui.router
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [moc.ui.authentication.login :refer [Login login]]
            [moc.ui.authentication.register :refer [Register register]]
            [moc.ui.not-found :refer [NotFound not-found]]))

(defn route->component [key]
  (case key
    :user/login [Login login]
    :user/register [Register register]
    [NotFound not-found]))

(defn route->query [key]
  (or (om/get-query (first (route->component key)))
      []))

(defui Router
  static om/IQueryParams
  (params [this]
    {:page-query (route->query :index)})
  static om/IQuery
  (query [this]
    '[:url {:page-query ?page-query}])
  Object
  (componentWillReceiveProps [this props]
    (let [old-route (:url (om/props this))
          new-route (:url props)]
      (when (not= old-route new-route)
        (om/set-query! this {:params {:page-query (route->query (:handler new-route))}}))))
  (render [this]
    (let [{:keys [url page-query]} (om/props this)
          handler (:handler url)]
      ((second (route->component handler)) page-query))))

