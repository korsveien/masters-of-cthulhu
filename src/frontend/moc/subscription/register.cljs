(ns moc.subscription.register
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-sub]])
  (:require-macros [reagent.ratom :refer [reaction]]))

(register-sub
 :register/form-state
 (fn [db _]
   (reaction (select-keys (-> @db :ui :user/register) [:email :success?]))))

(register-sub
 :register/form-errors
 (fn [db _]
   (reaction (-> @db :ui :user/register :errors))))
