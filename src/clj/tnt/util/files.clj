(ns tnt.util.files
  "Helper functions for working directly with files -- no wrapping concepts."
  (:require
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [clojure.string :as string]))

(defn is-emacs-temp-file? [file-name]
  (or (string/starts-with? file-name ".#")
      (string/ends-with? file-name "~")
      (and (string/starts-with? file-name "#")
           (string/ends-with? file-name "#"))))

(defn filter-temp-files [files]
  (filter #(not (is-emacs-temp-file? (.getName %))) files))

(defn read-edn
  "Load edn from an io/reader source (filename or io/resource)."
  [source]
  (try
    (with-open [r (io/reader source)]
      (edn/read (java.io.PushbackReader. r)))
    (catch java.io.IOException e
      (printf "Couldn't open '%s': %s\n" source (.getMessage e))
      (throw e))
    (catch RuntimeException e
      (printf "Error parsing edn file '%s': %s\n" source (.getMessage e))
      (throw e))))

(defn read-file
  "Simple wrapper around load-edn so it's easier to change if we want to include other file types."
  [source]
  (read-edn source))

(defn get-directory [dir]
  (cond
    (instance? java.io.File dir) dir
    (string? dir) (io/file (str "./resources/" dir))))

(defn list-files
  "List all relevant files within a directory.

  Ignores temporary files which shouldn't be committed to github anyway."
  [dir]
  (filter-temp-files (.listFiles (get-directory dir))))

(defn expand-directories [dir]
  (map (fn [f]
         (if (.isDirectory f)
           (expand-directories f)
           f))
       (list-files dir)))
