(ns moc.core
  (:require [com.stuartsierra.component :as component]
            [moc.http :refer [->Server]]
            [moc.envar :refer [->EnvironmentVariables]]
            [moc.db :refer [->Database]])
  (:gen-class))

(defn prod-system [envars]
  (component/system-map
   :envars envars
   :db (component/using
        (->Database nil nil nil)
        [:envars])
   :server (component/using
            (->Server nil nil nil)
            [:envars :db])))

(defn -main [& args]
  (component/start (prod-system (->EnvironmentVariables nil nil nil))))
