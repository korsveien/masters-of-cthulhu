(ns moc.subscription.user
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-sub]])
  (:require-macros [reagent.ratom :refer [reaction]]))

(register-sub
 :user/current
 (fn [db _]
   (reaction (-> @db :user/current))))
