(ns follow-the-money.application.adapters.group
  (:require
   [clojure.spec.alpha :as s]
   [follow-the-money.infrastructure.utils.misc :as utils.misc]
   [follow-the-money.domain.models.group :as group-specs]))

(defn group-request->model
  [{:keys [name currency]}]
  {::group-specs/name         name
   ::group-specs/currency     currency})

(s/fdef group-request->model
  :args (s/cat :group ::group-specs/request)
  :ret ::group-specs/model)

(defn model->group-data
  [{::group-specs/keys [id name currency created-at]}]
  {::group-specs/id           id
   ::group-specs/name         name
   ::group-specs/currency     (utils.misc/qualify-key "currency" currency)
   ::group-specs/created-at   created-at})

(s/fdef model->group-data
  :args (s/cat :group ::group-specs/model)
  :ret ::group-specs/data)

(defn group-data->model
  [{::group-specs/keys [id name currency created-at]}]
  {::group-specs/id         id
   ::group-specs/name       name
   ::group-specs/currency   (utils.misc/unqualify-key currency)
   ::group-specs/created-at created-at})

(s/fdef group-data->model
  :args (s/cat :group ::group-specs/data)
  :ret ::group-specs/model)

(defn model->group-response
  [{::group-specs/keys [id name currency]}]
  {:id           id
   :name         name
   :currency     currency})

(s/fdef model->group-response
  :args (s/cat :group ::group-specs/model)
  :ret ::group-specs/response)
