(ns moc.validate.user
  (:require [bouncer.validators :as v]
            [moc.validate.util :refer [validate]]))

(def password-validators
  [v/required v/string
   [v/min-count 6 :message "password should at least consist of six letters"]])

(def login-schema
  {:email [v/required v/email]
   :password password-validators})

(def register-schema
  {:email [v/required v/email]})

(defn validate-register-schema [{:keys [password confirm-password] :as item}]
  (let [errors (validate item login-schema)]
    (if (= password confirm-password)
      errors
      (assoc errors :confirm-password "passwords must match"))))
