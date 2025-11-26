(ns follow-the-money.domain.logic.user-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [follow-the-money.domain.logic.user :as logic.user]))

(deftest calculate-due-amount-in-group-test
  (testing "Given a user and their expenses in a group"
    (let [user {:id (random-uuid)}
          group-id (random-uuid)
          expenses [{:id (random-uuid)
                     :user-id (:id user)
                     :group-id group-id
                     :amount 100.11M}
                    {:id (random-uuid)
                     :user-id (:id user)
                     :group-id group-id
                     :amount 200.22M}
                    {:id (random-uuid)
                     :user-id (:id user)
                     :group-id group-id
                     :amount 333.33M}
                    {:id (random-uuid)
                     :user-id (:id user)
                     :group-id group-id
                     :amount 666.66M}]]
      (is (= 1300.32M (logic.user/calculate-due-amount-in-group expenses))))))
