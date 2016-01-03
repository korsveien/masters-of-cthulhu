(ns moc.db.user
  (:require [hugsql.core :refer [def-db-fns]]))

(def-db-fns "sql/user.sql" {:quoting :ansi})
(def-db-fns "sql/migrations/user.sql" {:quoting :ansi})

(def migrations {1451703379489 {:up m-setup-users!
                                :down m-drop-users!}})
