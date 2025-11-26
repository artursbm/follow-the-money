(ns follow-the-money.domain.logic.group-test 
  (:require
   [clojure.test :refer [deftest is testing]]
   [follow-the-money.domain.logic.group :as logic.group]))

(deftest update-total-balance-test
  (testing "Given a group with a balance, and a new expense"
    (let [curr-group {:name "group"
                      :currency :real
                      :total-amount 140.34M}
          new-expense {:amount 20M}]
      (testing "Then the group is returned with new total-amount"
        (is (= {:name "group"
                     :currency :real
                     :total-amount 160.34M}
                    (logic.group/update-total-balance curr-group new-expense))))
      (testing "And the total-amount has the new value"
        (is (= 160.34M
               (:total-amount (logic.group/update-total-balance curr-group new-expense))))))))
