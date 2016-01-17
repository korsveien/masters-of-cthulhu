(ns moc.style.core
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer [px]]
            [moc.style.palette :as color]
            [moc.style.common :as common]
            [moc.style.auth :as auth]))

(defstyles app
  [:* {:box-sizing :border-box}]
  [:html :body :#app :#app-wrapper {:height "100%"
                                    :margin 0
                                    :font-family "'Open Sans', sans-serif"
                                    :background color/base}]

  [:h1 :h2 :h3 {:margin 0
                :margin-bottom (px 20)}]

  [:a {:color :black}]

  [:.full-width {:width "100%"}]
  [:.right-breather {:margin-right (px 10)}]

  common/loading-page
  common/box
  common/input
  common/button
  common/sidebar-layout
  common/link-list

  auth/login-page
  auth/register-page)
