(ns moc.db.adapter
  (:require [hugsql.core :as hugsql]
            [hugsql.adapter.clojure-jdbc :as cj-adapter]
            [clj-time.coerce :as time.coerce]
            [jdbc.proto :as types]))

(hugsql/set-adapter! (cj-adapter/hugsql-adapter-clojure-jdbc))

(extend-protocol types/ISQLType
  org.joda.time.DateTime
  (as-sql-type [self conn]
    (time.coerce/to-sql-time self))
  (set-stmt-parameter! [self conn stmt index]
    (.setObject stmt index (types/as-sql-type self conn))))

(extend-protocol types/ISQLResultSetReadColumn
  java.sql.Timestamp
  (from-sql-type [self conn metadata i]
    (time.coerce/from-sql-time self)))
