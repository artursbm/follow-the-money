(ns follow-the-money.application.adapters.user-test
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.test.alpha :as stest]
   [clojure.test :refer [deftest is testing]]
   [clojure.test.check.generators :as generators]
   [follow-the-money.application.adapters.user :as adapters.user]
   [follow-the-money.domain.models.user :as user-specs]))

(stest/check `adapters.user/request->model)
(stest/check `adapters.user/model->data)
(stest/check `adapters.user/data->model)
(stest/check `adapters.user/model->response)

(deftest request->model-test
  (testing "Given a user request data"
    (let [user-request (generators/generate (s/gen ::user-specs/request))]
      (testing "Should map to a model with unqualified fields"
        (is (match? {::user-specs/first-name (:first-name user-request)
                     ::user-specs/last-name  (:last-name user-request)
                     ::user-specs/email      (:email user-request)
                     ::user-specs/password   (:password user-request)}
                    (adapters.user/request->model user-request)))))))

(deftest model->data-test
  (testing "Given a user model data"
    (let [user-model (generators/generate (s/gen ::user-specs/model))]
      (testing "Should map to a database schema with qualified fields"
        (is (match? {::user-specs/id         (::user-specs/id user-model)
                     ::user-specs/created-at (::user-specs/created-at user-model)
                     ::user-specs/first-name (::user-specs/first-name user-model)
                     ::user-specs/last-name  (::user-specs/last-name user-model)
                     ::user-specs/email      (::user-specs/email user-model)
                     ::user-specs/password   (::user-specs/password user-model)}
                    (adapters.user/model->data user-model)))))))

(deftest data->model-test
  (testing "Given a user data of database schema"
    (let [user-data (generators/generate (s/gen ::user-specs/data))]
      (testing "Should map to a model with unqualified fields"
        (is (match? {::user-specs/id         (::user-specs/id user-data)
                     ::user-specs/created-at (::user-specs/created-at user-data)
                     ::user-specs/first-name (::user-specs/first-name user-data)
                     ::user-specs/last-name  (::user-specs/last-name user-data)
                     ::user-specs/email      (::user-specs/email user-data)
                     ::user-specs/password   (::user-specs/password user-data)}
                    (adapters.user/data->model user-data)))))))

(deftest model->response-test
  (testing "Given a user model data"
    (let [user-model (generators/generate (s/gen ::user-specs/model))]
      (testing "Should map to a response schema with qualified fields"
        (is (match? {:id         (::user-specs/id user-model)
                     :first-name (::user-specs/first-name user-model)
                     :last-name  (::user-specs/last-name user-model)
                     :email      (::user-specs/email user-model)}
                    (adapters.user/model->response user-model)))))))
