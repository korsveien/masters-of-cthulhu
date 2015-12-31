(ns moc.ui.common.handlers
  (:require [re-frame.core :refer [dispatch]]))

(defn pass-to-dispatch [key]
  (fn [e]
    (dispatch [key (-> e .-target .-value)])))
