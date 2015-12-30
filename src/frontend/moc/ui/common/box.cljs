(ns moc.ui.common.box)

(defn box [{:keys [title footer]} & children]
  [:div.box
   [:div.content
    [:h2 title]
    children]
   (when footer
     [:div.footer footer])])
