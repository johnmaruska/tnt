(ns tnt.util.resources
  (:require [tnt.util.files :as uf]))

(defn base-file? [file]
  (= "base.edn" (.getName file)))

(defn separate-base-files [files]
  (reduce (fn [acc f]
            (cond
              (seq? f)       (update acc :rest conj (separate-base-files f))
              (base-file? f) (assoc acc :base f)
              :else          (update acc :rest conj f)))
          {:base nil :rest []}
          files))

(defn read-base [f]
  (when-let [base (:base f)]
    (uf/read-file base)))

(defn merge-bases
  ([f] (merge-bases {} f))
  ([base f]
   (cond
     ;; directories have already been expanded, java.io.File means it can be read
     (instance? java.io.File f)
     (merge base (uf/read-file f))
     ;; another layer to merge
     (map? f)
     (map (fn [x] (merge-bases (merge base (read-base f)) x))
          (:rest f)))))

(defn load-directory [dir]
  (-> dir
      uf/expand-directories
      separate-base-files
      merge-bases
      flatten))
