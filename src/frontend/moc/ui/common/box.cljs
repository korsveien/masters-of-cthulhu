(ns moc.ui.common.box
  (:refer-clojure :exclude [Box])
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui Box
  Object
  (render [this]
    (let [{:keys [title footer]} (om/props this)]
      (dom/div #js {:className "box"}
               (dom/h2 nil title)
               (om/children this)
               (when footer
                 (dom/div #js {:className "footer"}
                          footer))))))

(def box (om/factory Box))
