(ns moc.envar
  (:require [com.stuartsierra.component :as component]
            [moc.log :as log]))

(defrecord EnvironmentVariables [cpus port database-url]
  component/Lifecycle
  (start [self]
    (log/info "Retrieving environment variables")
    (assoc self
           :cpus (or cpus (.availableProcessors (Runtime/getRuntime)))
           :port (or port (Integer/parseInt (System/getenv "PORT")))
           :database-url (or database-url (System/getenv "DATABASE_URL"))))
  (stop [self]
    self))
