(ns moc.ui.common.button)

(defn- click-event [loading? handler]
  (fn [e]
    (.preventDefault e)
    (and (not loading?) handler (handler))))

(defn button [{:keys [is-block? loading? on-click]} text]
  [:a {:href "#"
       :class (str "button"
                   (if is-block? " full-width")
                   (if loading? " loading"))
       :on-click (click-event loading? on-click)}
   (if loading?
     "Loading..."
     text)])
