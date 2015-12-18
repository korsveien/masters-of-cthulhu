(defproject moc "0.1.0-SNAPSHOT"
  :description "A helper application for running Call of Cthulhu games"
  :url "http://github.com/Skinney/moc"
  :license "MIT License"

  :main moc.core
  :source-paths ["src/backend" "src/common"]
  :min-lein-version "2.5.0"
  :repl-options {:init-ns repl}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [bidi "1.23.1"]

                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]]

  :profiles {:dev {:dependencies [[reloaded.repl "0.2.1"]
                                  [garden "1.3.0"]

                                  [org.clojure/clojurescript "1.7.170"]
                                  [org.omcljs/om "1.0.0-alpha28"]
                                  [cljsjs/fastclick "1.0.6-0"]]

                   :plugins [[lein-cljsbuild "1.1.1"]
                             [lein-garden "0.2.6"]
                             [lein-figwheel "0.5.0-2"]]

                   :figwheel {:http-server-root "public"
                              :server-port 3001
                              :css-dirs ["resources/public/css"]}

                   :cljsbuild {:builds [{:id "dev"
                                         :source-paths ["src/frontend" "src/common"]
                                         :figwheel {:on-jsload "moc.core/reload!"}
                                         :compiler {:main "moc.core"
                                                    :asset-path "/js_tmp"
                                                    :output-to "resources/public/app.js"
                                                    :output-dir "resources/public/js_tmp"}}
                                        {:id "release"
                                         :source-paths ["src/frontend" "src/common"]
                                         :compiler {:output-to "resources/public/app.js"
                                                    :elide-asserts true
                                                    :optimizations :advanced}}]}

                   :garden {:builds [{:stylesheet moc.style.core/app
                                      :source-paths ["src/style"]
                                      :compiler {:output-to "resources/public/css/app.css"
                                                 :pretty-print? false}}]}}

             :uberjar {:aot [moc.core]
                       :omit-source true
                       :global-vars {*assert* false}}}

    :clean-targets ^{:protect false} ["resources/public/app.js"
                                      "resources/public/js_tmp"
                                      "resources/public/css/app.css"
                                      "target"])
