(ns repl
  (:require [clojure.repl :refer :all]
            [reloaded.repl :refer [system start stop reset reset-all]]
            [moc.core :refer [prod-system]]
            [moc.envar :refer [->EnvironmentVariables]]))

(def repl-envars (->EnvironmentVariables 1 3000 ""))

(reloaded.repl/set-init! (constantly (prod-system repl-envars)))
