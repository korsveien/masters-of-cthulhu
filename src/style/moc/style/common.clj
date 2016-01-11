(ns moc.style.common
  (:require [garden.units :refer [px]]
            [moc.style.palette :as color]))

(def loading-page
  [:.loading-page {:margin-top (px 50)
                   :text-align :center}])

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

(def sidebar-layout
  [:.sidebar-layout {:position :relative
                     :height "100%"}

   [:.sidebar {:position :relative
               :width (px 260)
               :height "100%"
               :background color/base}
    [:.dropdown {:list-style :none
                 :padding "5px 10px"
                 :margin 0
                 :height (px 40)
                 :line-height (px 30)
                 :background color/darker
                 :cursor :pointer}
     [:li {:position :relative}
      [:i.fa.fa-chevron-down {:position :absolute
                              :height (px 25)
                              :line-height (px 25)
                              :right (px 10)}]]]
    [:.sidebar-content {:padding (px 10)}]
    [:.footer {:position :absolute
               :bottom 0}]]

   [:.content {:position :absolute
               :background color/white
               :left (px 260)
               :right 0
               :top 0
               :bottom 0}
    [:.footer {:position :absolute
               :bottom 0}]]])
