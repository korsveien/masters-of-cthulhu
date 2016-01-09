(ns moc.subscription.routes
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-sub]])
  (:require-macros [reagent.ratom :refer [reaction]]))

(register-sub
 :route/info
 (fn [db _]
   (reaction (:route/info @db))))
