(ns moc.handler.loading
  (:require [re-frame.core :refer [register-handler]]))

(register-handler
 :loading/inc
 (fn [db _]
   (update db :loading-count inc)))

(register-handler
 :loading/dec
 (fn [db _]
   (update db :loading-count dec)))
