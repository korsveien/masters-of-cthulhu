(ns moc.util
  (:require [clj-time.format :as time.format]
            [cemerick.url :refer [url]]
            [moc.db.user :as db.user])
  (:import java.util.UUID))

(defn base-url [full-url]
  (-> (url full-url)
      (assoc :path nil)
      (str)))

(defn join-urls [base path]
  (url base path))

(defn date-time-format [time]
  (time.format/unparse (time.format/formatters :rfc822)
                       time))

(defn current-token [req]
  (when-let [token (get-in req [:cookies "moc" :value])]
    (UUID/fromString token)))

(defn current-user [{:keys [component/db] :as req}]
  (when-let [token (current-token req)]
    (let [user (db.user/get-by-token db {:fields ["users.id"
                                                  "name"
                                                  "email"
                                                  "password"]
                                         :token token})]
      (update user :password #(if % true false)))))

(defn current-user-id [{:keys [component/db] :as req}]
  (when-let [token (current-token req)]
    (:id (db.user/get-by-token db {:fields ["users.id"]
                                   :token token}))))
