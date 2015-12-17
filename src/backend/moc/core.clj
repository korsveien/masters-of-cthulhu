(ns moc.core
  (:require [com.stuartsierra.component :as component]
            [moc.http :refer [->Server]]
            [moc.envar :refer [->EnvironmentVariables]])
  (:gen-class))

(defn prod-system [envars]
  (component/system-map
   :envars envars
   :server (component/using
            (->Server nil nil nil)
            [:envars])))

(defn -main [& args]
  (component/start (prod-system (->EnvironmentVariables nil nil nil))))
