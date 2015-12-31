(ns moc.subscription.login
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-sub]])
  (:require-macros [reagent.ratom :refer [reaction]]))

(register-sub
 :login/form-state
 (fn [db _]
   (reaction (select-keys (-> @db :ui :user/login) [:email :password]))))

(register-sub
 :login/form-errors
 (fn [db _]
   (reaction (-> @db :ui :user/login :errors))))
