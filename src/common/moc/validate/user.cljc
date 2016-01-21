(ns moc.validate.user
  (:require [bouncer.validators :as v]
            [moc.validate.util :refer [validate]]))

(def password-validator
  [v/required v/string
   [v/min-count 8 :message "Password should at least contain eight letters"]])

(def register-schema
  {:email [v/required v/email]})

(def login-schema (assoc register-schema :password password-validator))

(def passwordless-profile-schema
  {:name [v/string
          [v/min-count 2 :message "Name must at least contain two letters"]
          [v/max-count 50 :message "Name cannot contain more than fifty letters"]]
   :email [v/required v/email]})

(def profile-schema (assoc passwordless-profile-schema :password password-validator))

(defn validate-profile [profile]
  (if (:change-password? profile)
    (let [{:keys [password confirm-password]} profile
          errors (validate profile profile-schema)]
      (if (= password confirm-password)
        errors
        (assoc errors :confirm-password "Must match password")))
    (validate profile passwordless-profile-schema)))
