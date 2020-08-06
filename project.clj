(defproject tnt "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.773"]
                 [compojure "1.6.2"]
                 [reagent "1.0.0-alpha2"]
                 [hiccup "1.0.5"]]
  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.2.11"]]
                   :resource-paths ["target"]
                   :clean-targets ^{:protect false} ["target"]}}
  :aliases {"fig" ["trampoline" "run" "-m" "figwheel.main"]
            "cljs-repl" ["fig" "--" "--build" "dev" "--repl"]}
  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["resources"])
