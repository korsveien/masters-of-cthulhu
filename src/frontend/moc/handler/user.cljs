(ns moc.handler.user
  (:require [re-frame.core :refer [dispatch register-handler]]
            [moc.ajax :as ajax]))

(register-handler
 :user/get-current
 (fn [db _]
   (ajax/request {:path [:api.user/me]
                  :on-success #(dispatch [:user/get-current-success %])})
   db))

(register-handler
 :user/get-current-success
 (fn [db [_ user]]
   (assoc db :user/current user)))
