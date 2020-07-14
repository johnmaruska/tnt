(ns tnt.server
  (:require [tnt.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(def port 3000)  ; TODO: make a config setting

(defn -main [& args]
  (run-jetty #'app {:port port :join? false}))
