(ns moc.ui.common.checkbox)

(defn checkbox [{:keys [label disabled? value error on-change]}]
  [:div.checkbox
   [:label
    [:input {:type :checkbox
             :disabled disabled?
             :value value
             :on-change (or on-change (fn [_] nil))}]
    label]
   (when error
     [:div.error
      [:i.fa.fa-warning]
      [:span.text error]])])
