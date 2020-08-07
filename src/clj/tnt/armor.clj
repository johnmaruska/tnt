(ns tnt.armor
  (:require
   [tnt.util.resources :as resources]))

(def resource-root "./resources/dungeons_and_dragons/armor")

(def all (resources/load-directory "dungeons_and_dragons/armor"))

;; TODO: group-by paths, e.g. [:armor :light :name]
