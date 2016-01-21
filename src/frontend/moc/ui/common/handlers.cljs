(ns moc.ui.common.handlers
  (:require [re-frame.core :refer [dispatch]]))

(defn pass-to-dispatch [e key]
  (if (= "checkbox" (-> e .-target .-type))
    (dispatch [key (not (= "true" (-> e .-target .-value)))])
    (dispatch [key (-> e .-target .-value)])))

(defn dispatch-on-enter [e msg]
  (when (= 13 (.-keyCode e))
    (dispatch msg)))
