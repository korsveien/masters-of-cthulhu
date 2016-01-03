(ns moc.ui.common.icon-input)

(defn icon-input [{:keys [icon type auto-focus placeholder disabled? value on-change error]}]
  [:div.icon-input-box
   [:div.icon
    [:i {:className (str "fa fa-" icon)}]]
   [:input {:type (or type "text")
            :auto-focus (or auto-focus false)
            :placeholder placeholder
            :disabled disabled?
            :value value
            :on-change on-change}]
   (when error
     [:div.error
      [:i.fa.fa-warning]
      [:span.text error]])])
