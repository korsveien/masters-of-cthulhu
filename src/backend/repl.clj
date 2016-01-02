(ns repl
  (:require [clojure.repl :refer :all]
            [reloaded.repl :refer [system start stop reset reset-all]]
            [moc.db :as db]
            [moc.core :refer [prod-system]]
            [repl-system :refer [envars]]))

(defn- db-spec []
  (-> system :db :spec))

(reloaded.repl/set-init! (constantly (prod-system envars)))
