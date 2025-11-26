(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application.

  Call `(reset)` to reload modified code and (re)start the system.

  The system under development is `system`, referred from
  `com.stuartsierra.component.repl/system`.

  See also https://github.com/stuartsierra/component.repl"
  (:require
   [clojure.pprint :as pprint]
   [clojure.tools.namespace.repl]
   [com.stuartsierra.component :as component]
   [com.stuartsierra.component.repl :refer [set-init]]
   [follow-the-money.follow-the-money]
   [clojure.spec.test.alpha :as stest]))

;; Do not try to load source code from 'resources' directory
(clojure.tools.namespace.repl/set-refresh-dirs "dev" "src" "test")
(add-tap pprint/pprint)
(stest/instrument)
(defn dev-system
  "Constructs a system map suitable for interactive development."
  []
  (component/system-map
   ;; TODO
   ))

(set-init (fn [_] (dev-system)))
