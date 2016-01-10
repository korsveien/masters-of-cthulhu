(defproject moc "0.1.0-SNAPSHOT"
  :description "A helper application for running Call of Cthulhu games"
  :url "http://github.com/Skinney/moc"
  :license "MIT License"

  :main moc.core
  :source-paths ["src/backend" "src/common"]
  :min-lein-version "2.5.0"
  :repl-options {:init-ns repl}

  :dependencies [[org.clojure/clojure "1.8.0-RC4"]
                 [com.stuartsierra/component "0.3.1"]
                 [bidi "1.25.0"]
                 [bouncer "1.0.0"]

                 [com.cemerick/url "0.1.1"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [com.cognitect/transit-clj "0.8.285"]
                 [clj-time "0.11.0"]
                 [crypto-password "0.1.3"]
                 [danlentz/clj-uuid "0.1.6"]
                 [com.layerware/hugsql-core "0.3.1"]
                 [com.layerware/hugsql-adapter-clojure-jdbc "0.3.1"]
                 [funcool/clojure.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4.1207"]
                 [com.zaxxer/HikariCP "2.4.3"]]

  :profiles {:dev {:dependencies [[reloaded.repl "0.2.1"]
                                  [garden "1.3.0"]

                                  [org.clojure/clojurescript "1.7.228"]
                                  [reagent "0.5.1"]
                                  [re-frame "0.6.0"]
                                  [com.andrewmcveigh/cljs-time "0.4.0"]
                                  [com.cognitect/transit-cljs "0.8.237"]]

                   :plugins [[lein-cljsbuild "1.1.2"]
                             [lein-garden "0.2.6"]
                             [lein-figwheel "0.5.0-3"]]

                   :figwheel {:http-server-root "public"
                              :server-port 3001
                              :css-dirs ["resources/public"]}

                   :cljsbuild {:builds [{:id "dev"
                                         :source-paths ["src/frontend" "src/common"]
                                         :figwheel {:on-jsload "moc.core/reload!"}
                                         :compiler {:main "moc.core"
                                                    :parallel-build true
                                                    :asset-path "/js_tmp"
                                                    :output-to "resources/public/app.js"
                                                    :output-dir "resources/public/js_tmp"}}
                                        {:id "release"
                                         :source-paths ["src/frontend" "src/common"]
                                         :compiler {:output-to "resources/public/app.js"
                                                    :elide-asserts true
                                                    :closure-defines {"goog.DEBUG" false}
                                                    :parallel-build true
                                                    :optimizations :advanced}}]}

                   :garden {:builds [{:stylesheet moc.style.core/app
                                      :source-paths ["src/style"]
                                      :compiler {:output-to "resources/public/app.css"
                                                 :pretty-print? false}}]}}

             :uberjar {:aot [moc.core]
                       :omit-source true
                       :global-vars {*assert* false}}}

    :clean-targets ^{:protect false} ["resources/public/app.js"
                                      "resources/public/js_tmp"
                                      "resources/public/app.css"
                                      "target"])
