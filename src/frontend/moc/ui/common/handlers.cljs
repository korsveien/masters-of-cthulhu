(ns moc.ui.common.handlers)

(defn pass-to-state! [state key]
  (fn [e]
    (swap! state assoc key (-> e .-target .-value))))
