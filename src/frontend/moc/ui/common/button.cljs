(ns moc.ui.common.button
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui Button
  Object
  (onClick [this handler]
    (let [{:keys [loading?]} (om/props this)]
      (fn [e]
        (.preventDefault e)
        (and (not loading?) handler (handler)))))

  (render [this]
    (let [{:keys [loading?]} (om/props this)
          {:keys [on-click]} (om/get-computed this)]
      (dom/a #js {:href "#"
                  :className (if loading? "button loading" "button")
                  :onClick (.onClick this on-click)}
             (if loading?
               "Loading..."
               (om/children this))))))

(def button (om/factory Button))
