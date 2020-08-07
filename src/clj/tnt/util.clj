(ns tnt.util
  (:require
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clojure.string :as string]))

(defn is-emacs-temp-file? [file-name]
  (or (string/starts-with? file-name ".#")
      (string/ends-with? file-name "~")
      (and (string/starts-with? file-name "#")
           (string/ends-with? file-name "#"))))

(defn remove-emacs-temp-files [files]
  (filter #(not (is-emacs-temp-file? (.getName %))) files))

(defn load-edn
  "Load edn from an io/reader source (filename or io/resource)."
  [source]
  (try
    (with-open [r (io/reader source)]
      (edn/read (java.io.PushbackReader. r)))
    (catch java.io.IOException e
      (printf "Couldn't open '%s': %s\n" source (.getMessage e)))
    (catch RuntimeException e
      (printf "Error parsing edn file '%s': %s\n" source (.getMessage e)))))

(defn list-resources
  ([]
   (list-resources ""))
  ([directory-name]
   (let [path (str "./resources/" directory-name)]
     (remove-emacs-temp-files (.listFiles (io/file path))))))
