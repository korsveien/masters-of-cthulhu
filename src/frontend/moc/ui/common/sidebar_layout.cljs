(ns moc.ui.common.sidebar-layout)

(defn sidebar [opts]
  [:div.sidebar
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
  [:div.sidebar-layout
   [sidebar (:sidebar opts)]
   (into [:div.content
          [:div.footer
           (or (-> opts :content :footer) [:div "Default Footer"])]]
         children)])
