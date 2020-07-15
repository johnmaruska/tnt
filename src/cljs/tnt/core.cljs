(ns tnt.core
  (:require
   [reagent.dom :as rdom]))

(enable-console-print!)

(println "This text is printed from src/tnt/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defn mount-root []
  ;; (rdom/render [current-page] (.getElementById js/document "app"))
  )

(defn on-js-reload []
  (mount-root))
