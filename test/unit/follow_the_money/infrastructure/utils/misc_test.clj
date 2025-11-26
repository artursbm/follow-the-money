(ns follow-the-money.infrastructure.utils.misc-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [follow-the-money.infrastructure.utils.misc :as utils.misc]
   [matcher-combinators.test :refer [match?]]))

(deftest qualify-key-test
  (testing "Given an unqualified key and a base-ns"
    (let [un-key  :euro
          base-ns "currency"]
      (testing "Should return a qualified keyword"
        (is (match? :currency/euro
                    (utils.misc/qualify-key base-ns un-key)))))))
