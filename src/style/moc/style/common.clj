(ns moc.style.common
  (:require [garden.units :refer [px]]
            [garden.stylesheet :refer [at-media]]
            [moc.style.palette :as color]))

(def loading-page
  [:.loading-page {:position :fixed
                   :overflow :hidden
                   :width "100%"
                   :height 0
                   :text-align :center
                   :background color/base
                   :z-index 1000
                   :transition "height 300ms ease-in-out"}
   [:&.visible {:height "100%"}]
   [:h2 {:padding-top (px 50)}]])

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

(def input
  [:.input-box {:position :relative
                :margin-bottom (px 20)}
   [:label {:display :block}]
   [:.icon {:position :absolute
            :left (px 1)
            :top (px 1)
            :background color/darker
            :height (px 42)
            :width (px 42)}
    [:.fa {:line-height (px 44)
           :font-size (px 22)
           :color color/lighter}]]
   [:input {:display :inline-block
            :height (px 44)
            :min-width (px 200)
            :padding "6px 12px"
            :font-size (px 14)
            :box-shadow :none
            :outline 0
            :border (str "1px solid " color/darker)}
    [:&.with-icon {:padding "6px 12px 6px 50px"}]
    [:&:focus {:outline (str "double " color/base)}]]
   [:.error {:text-align :left
             :margin-top (px 5)}
    [:.fa {:margin-right (px 10)}]
    [:.text {:display :inline-block}
     [:&:first-letter {:text-transform :capitalize}]]]])

(def button
  [:.button {:display :inline-block
             :background color/darker
             :text-decoration :none
             :color color/light
             :padding (px 10)
             :border-radius (px 5)
             :min-width (px 200)
             :text-align :center}
   [:&.loading {:background color/base}]])

(def sidebar-layout
  [:.sidebar-layout {:position :relative
                     :height "100%"}

   [:.sidebar {:position :absolute
               :width (px 260)
               :left (px 0)
               :height "100%"
               :background color/base
               :transition "left 150ms ease-in-out"}
    (at-media {:screen true
               :max-width (px 768)}
              [:&.hidden {:left (px -260)}])
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
               :bottom 0
               :transition "left 150ms ease-in-out"}
    [:.footer {:position :absolute
               :bottom 0}]
    [:.children {:padding (px 10)}]
    (at-media {:screen true
               :max-width (px 768)}
              [:&.hidden {:left (px 0)}])
    (at-media {:screen true
               :min-width (px 768)}
              [:.sidebar-toggle {:display :none}])]])

(def link-list
  [:ul.link-list {:padding 0
                  :margin 0
                  :list-style :none}
   [:li {:border-bottom "1px solid"}
    [:a {:display :block
         :text-decoration :none
         :color :black
         :padding (px 5)}
     [:&:hover {:background color/darker}]
     [:&:active {:color color/lighter}]]]])
