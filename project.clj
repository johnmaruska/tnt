(defproject tnt "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.773"]
                 ;; [org.clojure/core.async  "0.4.500"]
                 [hiccup "1.0.5"]
                 [reagent "1.0.0-alpha2"]
                 [metosin/reitit "0.5.1"]
                 [ring "1.8.1"]]

  :plugins [[lein-figwheel "0.5.20"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :main tnt.server
  :source-paths ["src/clj" "src/cljs"]

  :cljsbuild {:builds
              [{:id           "dev"
                :source-paths ["src/cljs"]

                :figwheel {:on-jsload "tnt.core/on-js-reload"
                           ;; pop open your application in the default browser
                           ;; once Figwheel has started your compiled application.
                           :open-urls ["http://localhost:3449/index.html"]}

                :compiler {:main                 tnt.core
                           :target               :bundle
                           :asset-path           "js/compiled/out"
                           :output-to            "resources/public/js/compiled/out/index.js"
                           :output-dir           "resources/public/js/compiled/out"
                           :bundle-cmd           {:none    ["npx.cmd" "webpack" "--mode=development"]
                                                  :default ["npx.cmd" "webpack"]}
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads             [devtools.preload]}}
               ;; This next build is a compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id           "min"
                :source-paths ["src/cljs"]
                :compiler     {:output-to     "resources/public/js/compiled/tnt.js"
                               :main          tnt.core
                               :optimizations :advanced
                               :pretty-print  false}}]}

  :figwheel {:http-server-root "public"
             :ring-handler     tnt.handler/app
             :server-port      3449
             ;; :server-ip "127.0.0.1"
             :css-dirs         ["resources/public/css"]}

  :profiles {:dev {:dependencies  [[binaryage/devtools "1.0.0"]
                                   [figwheel-sidecar "0.5.20"]]
                   :source-paths  ["src" "dev"]
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
1
