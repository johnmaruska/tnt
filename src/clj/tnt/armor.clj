(ns tnt.armor
  (:require
   [tnt.util.resources :as resources]))

(def all (resources/load-directory "dungeons_and_dragons/armor"))

;; TODO: group-by paths, e.g. [:armor :light :name]
