(ns tnt.armor
  (:require
   [tnt.util :as util]))

(def resource-root "./resources/dungeons_and_dragons/armor")

(defn read-armor-base []
  (util/load-edn (str resource-root "/base.edn")))
(def armor-base (memoize read-armor-base))

(defn base-file? [file] (= "base.edn" (.getName file)))

(defn separate-base-file
  "Converts a list of files to a map {:base File :rest [Files]} where the base
  file is whichever file is named `base.edn`.

  This is expected to only be applied on a directory of files, and assumes only
  one `base.edn` file."
  [files]
  (reduce (fn [acc file]
            (if (base-file? file)
              (assoc acc :base file)
              (update acc :rest conj file)))
          {:base nil :rest []}
          files))

(defn files->maps [files]
  (let [base (util/load-edn (:base files))]
    (map (fn [file]
           (->> file
                util/load-edn
                (merge base)))
         (:rest files))))

(defn maps->map-by-key
  "Converts a sequence of maps `ms` to a map of maps keyed by `k`
  [{:a A :b 1} {:a B :b 2}] => {A {:a A :b 1}, B {:a B :b 2}}"
  [ms k]
  (reduce (fn [acc x] (assoc acc (get x k) x)) {} ms))

(defn load-directory [dir]
  (-> dir
      util/list-resources
      separate-base-file
      files->maps
      (maps->map-by-key :name)))

(def light-armor (load-directory "dungeons_and_dragons/armor/light"))

;; same for medium, heavy

;; TODO: generalize an extra layer, load "all armor"
