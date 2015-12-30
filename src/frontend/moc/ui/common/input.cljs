(ns moc.ui.common.input)

(defn input [{:keys [type auto-focus placeholder]}]
  [:div.input-box
   [:input {:type (or type "text")
            :auto-focus (or auto-focus false)
            :placeholder placeholder}]])
