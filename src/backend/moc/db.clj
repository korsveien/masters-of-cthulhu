(ns moc.db
  (:require [com.stuartsierra.component :as component]
            [jdbc.core :as jdbc]
            [hugsql.core :refer [def-db-fns]]
            [clj-time.core :as time]
            [moc.log :as log]
            [moc.db.adapter]
            [moc.db.user :as db.user]
            [moc.db.token :as db.token])
  (:import com.zaxxer.hikari.HikariDataSource
           java.net.URI))

(def-db-fns "sql/migrations/version.sql" {:quoting :ansi})
(def migrations (merge db.user/migrations
                       db.token/migrations))

(defn run-migrations! [db-spec]
  (setup-versions! db-spec)
  (let [sorted-keys (sort (keys migrations))
        installed-versions (set (map :version (all-versions db-spec)))]
    (jdbc/atomic db-spec
      (doseq [version (remove installed-versions sorted-keys)]
        (log/info (str "Installing migration: " version))
        ((:up (get migrations version)) db-spec)
        (install-version! db-spec {:version version})))))

(defn last-installed-migration [db-spec]
  (last (sort (map :version (all-versions db-spec)))))

(defn rollback! [db-spec]
  (let [last-version (last-installed-migration db-spec)]
    (when-let [migration (get migrations last-version)]
      (log/info (str "Rolling back migration: " last-version))
      (jdbc/atomic db-spec
        ((:down migration) db-spec)
        (uninstall-version! db-spec {:version last-version})))))

(defn rollback-to! [db-spec version]
  (let [all-versions (sort (keys migrations))
        rollback (reverse (filter #(> % version) all-versions))]
    (jdbc/atomic db-spec
      (doseq [version rollback]
        (log/info (str "Rolling back migration: " version))
        ((:down (get migrations version)) db-spec)
        (uninstall-version! db-spec {:version version})))))

(defn- parse-db-uri [uri]
  (let [db-uri (URI. uri)
        user-info (-> db-uri .getUserInfo (.split ":"))
        username (first user-info)
        password (second user-info)]
    {:db-uri (str "jdbc:postgresql://"
                  (.getHost db-uri) ":" (.getPort db-uri) (.getPath db-uri))
     :username username
     :password password}))

(defn- setup-connection-pool [envars pool-size]
  (let [{:keys [db-uri username password]} (parse-db-uri (:database-url envars))]
    (jdbc/connection (doto (HikariDataSource.)
                       (.setJdbcUrl db-uri)
                       (.setUsername username)
                       (.setPassword password)
                       (.setMaximumPoolSize pool-size)))))

(defrecord Database [spec scheduler envars]
  component/Lifecycle
  (start [self]
    (let [pool-size (min 4 (max 1 (:cpus envars)))
          db-spec (setup-connection-pool envars pool-size)]
      (log/info (str "Initializing database with " pool-size " connections"))
      (run-migrations! db-spec)
      (assoc self
             :spec db-spec
             :scheduler scheduler)))

  (stop [self]
    (log/info "Shutting down database")
    (when spec
      (.close spec))
    self))
