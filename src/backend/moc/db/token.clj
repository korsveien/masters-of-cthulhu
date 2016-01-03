(ns moc.db.token
  (:require [hugsql.core :refer [def-db-fns]]))

(def-db-fns "sql/token.sql" {:quoting :ansi})
(def-db-fns "sql/migrations/token.sql" {:quoting :ansi})

(def migrations {1451703471212 {:up m-setup-tokens!
                                :down m-drop-tokens!}})
