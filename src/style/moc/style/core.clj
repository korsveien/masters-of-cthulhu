(ns moc.style.core
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer [px]]
            [moc.style.palette :as color]
            [moc.style.common :as common]
            [moc.style.auth :as auth]))

(defstyles app
  [:* {:box-sizing :border-box}]
  [:html :body :#app {:height "100%"
                      :margin 0
                      :font-family "'Open Sans', sans-serif"
                      :background color/base}]

  [:h1 :h2 :h3 {:margin 0
                :margin-bottom (px 20)}]

  [:.right-breather {:margin-right (px 10)}]

  common/loading-page
  common/box
  common/icon-input
  common/button
  common/sidebar-layout

  auth/login-page
  auth/register-page)
