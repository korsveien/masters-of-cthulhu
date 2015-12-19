(ns moc.style.core
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer [px]]
            [moc.style.common :as common]))

(defstyles app
  [:* {:box-sizing :border-box}]
  [:html :body :#app {:height "100%"
                      :margin 0
                      :font-family "Merriweather Sans"
                      :background "#f3f3f3"}]

  [:h1 :h2 :h3 {:margin 0
                :margin-bottom (px 20)}]

  common/box
  common/icon-input
  common/button)
