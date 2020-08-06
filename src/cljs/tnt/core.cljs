(ns ^:figwheel-hooks tnt.core
  (:require [reagent.dom :as rdom]))

(enable-console-print!)

(defn child []
  [:p "Hi, I am child"])

(defn mount []
  (rdom/render [child] (js/document.getElementById "app")))

;; calls after each save, not on start
(defn ^:after-load re-render [] (mount))
;; calls only on start
(defonce start-up (do (mount) true))
