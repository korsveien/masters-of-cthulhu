(ns moc.handler.user
  (:require [re-frame.core :refer [dispatch register-handler]]
            [moc.ajax :as ajax]
            [moc.router :as router]))

(register-handler
 :user/get-current
 (fn [db _]
   (ajax/request {:path [:api.user/me]
                  :on-success #(dispatch [:user/get-current-success %])})
   db))

(register-handler
 :user/get-current-success
 (fn [db [_ user]]
   (when (and (= :url.dashboard/index (-> db :route/info :handler))
              (not (:password user)))
     (router/navigate! [:url.dashboard/profile]))
   (assoc db :user/current user)))

(register-handler
 :user/logout
 (fn [db _]
   (ajax/request {:path [:api.auth/logout]})
   (assoc db :user/current nil)))
