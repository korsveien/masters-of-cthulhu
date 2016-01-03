(ns moc.subscription.loading
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-sub]])
  (:require-macros [reagent.ratom :refer [reaction]]))

(register-sub
 :loading?
 (fn [db _]
   (reaction (not (zero? (:loading-count @db))))))
