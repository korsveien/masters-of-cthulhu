(ns moc.ui.common.input)

(defn input [{:keys [icon is-block? type auto-focus label placeholder disabled? value error on-change]}]
  [:div.input-box
   (when label
     [:label label])
   (when icon
     [:div.icon
      [:i {:className (str "fa fa-" icon)}]])
   [:input {:type (or type "text")
            :auto-focus (or auto-focus false)
            :class (cond icon "with-icon full-width"
                         is-block? "full-width")
            :placeholder placeholder
            :disabled disabled?
            :value value
            :on-change (or on-change (fn [_] nil))}]
   (when error
     [:div.error
      [:i.fa.fa-warning]
      [:span.text error]])])
