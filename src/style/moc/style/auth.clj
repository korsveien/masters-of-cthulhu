(ns moc.style.auth
  (:require [garden.units :refer [px]]))

(def login-page
  [:.login-page {:padding-top (px 20)}
   [:.logo {:text-align :center}]])

(def register-page
  [:.register-page {:padding-top (px 20)}
   [:.logo {:text-align :center}]])
