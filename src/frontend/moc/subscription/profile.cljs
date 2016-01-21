(ns moc.subscription.profile
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-sub]])
  (:require-macros [reagent.ratom :refer [reaction]]))

(register-sub
 :profile/form-state
 (fn [db _]
   (reaction (dissoc (-> @db :ui :user/profile) :errors))))

(register-sub
 :profile/form-errors
 (fn [db _]
   (reaction (-> @db :ui :user/profile :errors))))
