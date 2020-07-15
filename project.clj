(defproject tnt "0.1.0-SNAPSHOT"
  :description "Toil & Trouble - Dungeons & Dragons character sheets and more."
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 [hiccup "1.0.5"]
                 [reagent "1.0.0-alpha2"]
                 [metosin/reitit "0.5.5"]
                 [ring "1.8.1"]]

  :plugins [[lein-figwheel "0.5.20"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :main tnt.server
  :source-paths ["src/clj" "src/cljs"]

  :cljsbuild {:builds
              [{:id           "dev"
                :source-paths ["src/cljs"]

                :figwheel {:on-jsload "tnt.core/on-js-reload"
                           :open-urls ["http://localhost:3449/index.html"]}

                :compiler {:main                 tnt.core
                           :target               :bundle
                           :asset-path           "js/compiled/out"
                           :output-to            "resources/public/js/compiled/out/index.js"
                           :output-dir           "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads             [devtools.preload]}}]}

  :figwheel {:ring-handler tnt.handler/app
             :css-dirs     ["resources/public/css"]}

  :profiles {:dev {:dependencies  [[binaryage/devtools "1.0.0"]
                                   [figwheel-sidecar "0.5.20"]]
                   :source-paths  ["src" "dev"]
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
1
