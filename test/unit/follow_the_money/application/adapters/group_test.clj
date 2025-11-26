(ns follow-the-money.application.adapters.group-test
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.test.alpha :as stest]
   [clojure.test :refer [deftest is testing]]
   [clojure.test.check.generators :as generators]
   [follow-the-money.application.adapters.group :as adapters.group]
   [follow-the-money.domain.models.group :as group-specs]
   [matcher-combinators.test :refer [match?]]))

(stest/check `adapters.group/group-request->model)
(stest/check `adapters.group/model->group-data)
(stest/check `adapters.group/group-data->model)
(stest/check `adapters.group/model->group-response)

(deftest group-request->model-test
  (testing "Given a group request data"
    (let [group-request (generators/generate (s/gen ::group-specs/request))]
      (testing "Should map to a model with unqualified fields"
        (is (match? {::group-specs/name         (:name group-request)
                     ::group-specs/currency     (:currency group-request)}
                    (adapters.group/group-request->model group-request)))))))

(deftest model->group-data-test
  (testing "Given a group model data"
    (let [group-model (generators/generate (s/gen ::group-specs/model))]
      (testing "Should map to a database schema with qualified fields"
        (is (match? {::group-specs/name         (::group-specs/name group-model)
                     ::group-specs/currency     (keyword "currency" (name (::group-specs/currency group-model)))}
                    (adapters.group/model->group-data group-model)))))))

(deftest group-data->model-test
  (testing "Given a group data of database schema"
    (let [group-data (generators/generate (s/gen ::group-specs/data))]
      (testing "Should map to a model with unqualified fields"
        (is (match? {::group-specs/id           (::group-specs/id group-data)
                     ::group-specs/name         (::group-specs/name group-data)
                     ::group-specs/currency     (keyword (name (::group-specs/currency group-data)))}
                    (adapters.group/group-data->model group-data)))))))

(deftest model->group-response-test
  (testing "Given a group model data"
    (let [group-model (generators/generate (s/gen ::group-specs/model))]
      (testing "Should map to a response schema with qualified fields"
        (is (match? {:id           (::group-specs/id group-model)
                     :name         (::group-specs/name group-model)
                     :currency     (::group-specs/currency group-model)}
                    (adapters.group/model->group-response group-model)))))))
