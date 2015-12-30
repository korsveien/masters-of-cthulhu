(ns moc.ui.common.box)

(defn box [{:keys [title footer]} & children]
  [:div.box
   (into [:div.content
          [:h2 title]]
         children)
   (when footer
     [:div.footer footer])])
