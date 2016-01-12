(ns moc.ui.common.sidebar-layout
  (:require [reagent.core :as reagent]))

(defn sidebar [visible? opts]
  [:div.sidebar {:className (if visible? "" "hidden")}
   (into [:ul.dropdown]
         (for [{:keys [title icon href]} (:dropdown opts)]
           [:li
            [:i.fa {:class (str "fa-" icon " right-breather")}]
            title
            [:i.fa.fa-chevron-down]]))
   [:div.sidebar-content
    (:content opts)]
   [:div.footer
    (:footer opts)]])

(defn sidebar-layout [opts & children]
  (let [sidebar-visible? (reagent/atom false)]
    (fn [opts & children]
      [:div.sidebar-layout
       [sidebar @sidebar-visible? (:sidebar opts)]
       (into [:div.content {:className (if @sidebar-visible? "" "hidden")}
              [:div.header
               [:i.fa.fa-bars.sidebar-toggle {:on-click #(swap! sidebar-visible? not)}]
               (-> opts :content :header)]
              [:div.footer
               (-> opts :content :footer)]]
             children)])))
