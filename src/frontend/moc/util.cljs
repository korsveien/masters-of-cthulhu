(ns moc.util
  (:require [goog.dom :as gdom]))

(defn render [comp]
  (js/ReactDOM.render comp (gdom/getElement "app")))
