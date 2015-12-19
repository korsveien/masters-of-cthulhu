(ns moc.style.common
  (:require [garden.units :refer [px]]))

(def box
  [:.box {:width (px 350)
          :margin "0 auto"
          :text-align :center
          :background "#fff"
          :border-radius (px 5)
          :overflow :hidden}
   [:.content {:padding (px 20)}]
   [:.footer {:background "#e0e0e0"
              :border-top "1px solid #ccc"
              :color "#878787"
              :padding (px 20)}]])

(def icon-input
  [:.icon-input-box {:position :relative
                     :margin-bottom (px 20)
                     :height (px 44)}
   [:.icon {:position :absolute
            :left (px 1)
            :top (px 1)
            :background "#ccc"
            :height (px 42)
            :width (px 42)}
    [:.fa {:line-height (px 44)
           :font-size (px 22)}]]
   [:input {:height "100%"
            :width "100%"
            :padding "6px 12px"
            :font-size (px 14)
            :padding-left (px 50)}]])

(def button
  [:.button {:display :block
             :background "#0099cc"
             :text-decoration :none
             :color :white
             :padding (px 10)
             :border-radius (px 5)}])
