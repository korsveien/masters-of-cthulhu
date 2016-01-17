(ns moc.routes.user
  (:require [clojure.string :as str]
            [clj-uuid :as uuid]
            [clj-time.core :as ct]
            [bidi.bidi :as bidi]
            [ring.util.response :as ru]
            [moc.routes.dispatch :refer [routes]]
            [moc.validate.util :refer [validate]]
            [moc.validate.user :as validate.user]
            [moc.db.user :as db.user]
            [moc.db.token :as db.token]
            [moc.mail :refer [send-mail!]]
            [moc.util :as util]
            [moc.urls :refer [urls]])
  (:import java.util.UUID))

(defn login-url [req token]
  (util/join-urls (util/base-url (get-in req [:headers "referer"]))
                  (bidi/path-for urls :url.auth/token :token token)))

(defn login-msg [link valid-to]
  (str "A login url has been generated for you at " (util/base-url link)
       "\n\n"
       "Please follow this link to login: " link
       "\n\n"
       "The link will be active until clicked, or until: " (util/date-time-format valid-to)
       "\n\n"
       "---\n"
       "This email has been generated. Do not respond to this email."))

(defn- send-login-url! [req email token valid-to]
  (send-mail! {:to email
               :subject "Login instructions"
               :body (login-msg (login-url req token) valid-to)}))

(defn ensure-user-login [db email-map]
  (let [email-query (assoc email-map :fields ["id"])
        user (or (db.user/get-by-email db email-query)
                 (db.user/create<! db email-query))
        token (uuid/v4)
        valid-to (ct/plus (ct/now)
                          (ct/days 1))]
    (db.token/create! db {:token token
                          :user-id (:id user)
                          :valid-to valid-to})
    [token valid-to]))

(defmethod routes :api.auth/register [{:keys [component/db params] :as req}]
  (if-let [errors (validate params validate.user/register-schema)]
    {:status 400
     :body errors}
    (let [normalized-email-params (update params :email #(-> %
                                                             (str/trim)
                                                             (str/lower-case)))
          [token valid-to] (ensure-user-login db normalized-email-params)]
      (send-login-url! req (:email normalized-email-params) token valid-to)
      {:status 200})))

(defmethod routes :url.auth/token [{:keys [component/db params]}]
  (let [token-param (update params :token #(UUID/fromString %))
        token (db.token/get-valid-by-id db token-param)]
    (if token
      (let [login-token (uuid/v4)
            user-id (:user-id token)]
        (db.user/update-logged-in! db {:id user-id})
        (db.token/create! db {:token login-token
                              :user-id user-id
                              :valid-to (ct/plus (ct/now)
                                                 (ct/days 3))})
        (-> (ru/redirect "/")
            (ru/set-cookie "moc" (str login-token)
                           {:path "/"
                            :max-age (* 60 60 24 3)})))
      {:status 404})))

(defmethod routes :api.auth/login [req]
  {:status 400})

(defmethod routes :api.user/me [req]
  (if-let [user (util/current-user req)]
    {:status 200
     :body user}
    {:status 401}))
