(ns vr-game.core
  (:require [reagent.core :as r]
            [reagent.dom.client :as rdom]
            ["@react-three/fiber" :refer [Canvas]]
            ["@react-three/xr" :refer [XR createXRStore]]
            ["react" :refer [useState]]))


(defonce store (createXRStore))

(defn xr-root []
  (let [[red setRed] (useState false)]
    [:<>
     [:button {:on-click #(.enterAR store)}
      "Enter AR"]
     [:> Canvas
      [:> XR {:store store}
       [:mesh {:pointerEventsType {:deny "grab"}
               :on-click #(setRed (not red))
               :position [0 1 -3]}
        [:boxGeometry]
        [:meshBasicMaterial {:color (if red "red" "blue")}]]]]]))

(defn app-component []
  [:f> xr-root])

(defonce root (rdom/create-root (.getElementById js/document "app-container")))

(defn ^:export main []
  (rdom/render root [app-component]))
