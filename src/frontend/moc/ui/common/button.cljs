(ns moc.ui.common.button)

(defn- click-event [loading? handler]
  (fn [e]
    (.preventDefault e)
    (and (not loading?) handler (handler))))

(defn button [{:keys [loading? on-click]} & children]
  [:a {:href "#"
       :class (if loading? "button loading" "button")
       :on-click (click-event loading? on-click)}
   (if loading?
     "Loading..."
     children)])
