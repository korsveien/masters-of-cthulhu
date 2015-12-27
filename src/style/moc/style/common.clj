(ns moc.style.common
  (:require [garden.units :refer [px]]
            [moc.style.palette :as color]))

(def box
  [:.box {:width (px 350)
          :margin "0 auto"
          :text-align :center
          :background :white
          :border-radius (px 5)
          :overflow :hidden}
   [:.content {:padding (px 20)}]
   [:.footer {:background color/darker
              :color color/dark
              :padding (px 20)}]])

(def icon-input
  [:.icon-input-box {:position :relative
                     :margin-bottom (px 20)}
   [:.icon {:position :absolute
            :left (px 1)
            :top (px 1)
            :background color/darker
            :height (px 42)
            :width (px 42)}
    [:.fa {:line-height (px 44)
           :font-size (px 22)
           :color color/lighter}]]
   [:input {:height (px 44)
            :width "100%"
            :padding "6px 12px"
            :font-size (px 14)
            :padding-left (px 50)
            :box-shadow :none
            :outline 0
            :border (str "1px solid " color/darker)}
    [:&:focus {:outline (str "double " color/base)}]]
   [:.error {:text-align :left
             :margin-top (px 5)}
    [:.fa {:margin-right (px 10)}]
    [:.text {:display :inline-block}
     [:&:first-letter {:text-transform :capitalize}]]]])

(def button
  [:.button {:display :block
             :background color/darker
             :text-decoration :none
             :color color/light
             :padding (px 10)
             :border-radius (px 5)}
   [:&.loading {:background color/base}]])
