(defproject follow-the-money "0.1.0-SNAPSHOT"
  :description "Budget sharing and group expense management."
  :url "TODO"
  :license {:type "Proprietary"}
  :dependencies [[org.clojure/clojure "1.12.3"]
                 [com.stuartsierra/component "1.2.0"]]
  :source-paths ["src"]
  :resource-paths ["resources"]
  :test-paths ["test/unit" "test/integration"]
  :profiles {:uberjar     {:aot :all}
             :dev         {:dependencies   [[org.clojure/tools.namespace "1.5.0"]
                                            [com.stuartsierra/component.repl "1.0.0"]
                                            [nubank/matcher-combinators "3.9.2"]
                                            [org.clojure/test.check "1.1.1"]]
                           :source-paths   ["dev" "test/helpers/"]
                           :resource-paths ["test/resources/"]
                           :repl-options   {:init-ns user}}
             :integration {:test-paths ^:replace ["test/integration/"]}
             :unit        {:test-paths ^:replace ["test/unit/"]}}
  :main ^{:skip-aot false} follow-the-money
  :aliases {"run-dev"         ["with-profile" "+repl-start" "trampoline" "repl" ":headless"]
            "run-dev-notramp" ["with-profile" "+repl-start" "repl" ":headless"]
            "unit"            ["with-profile" "+unit" "test"]
            "integration"     ["with-profile" "+integration" "test"]})
