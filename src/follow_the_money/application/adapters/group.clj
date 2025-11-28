(ns follow-the-money.application.adapters.group
  (:require
   [clojure.spec.alpha :as s]
   [follow-the-money.infrastructure.utils.misc :as utils.misc]
   [follow-the-money.domain.models.group :as group-specs]))

(defn request->model
  [{:keys [name currency]}]
  {::group-specs/name         name
   ::group-specs/currency     currency})

(s/fdef request->model
  :args (s/cat :group ::group-specs/request)
  :ret ::group-specs/model)

(defn model->data
  [{::group-specs/keys [id name currency created-at]}]
  {::group-specs/id           id
   ::group-specs/name         name
   ::group-specs/currency     (utils.misc/qualify-key "currency" currency)
   ::group-specs/created-at   created-at})

(s/fdef model->data
  :args (s/cat :group ::group-specs/model)
  :ret ::group-specs/data)

(defn data->model
  [{::group-specs/keys [id name currency created-at]}]
  {::group-specs/id         id
   ::group-specs/name       name
   ::group-specs/currency   (utils.misc/unqualify-key currency)
   ::group-specs/created-at created-at})

(s/fdef data->model
  :args (s/cat :group ::group-specs/data)
  :ret ::group-specs/model)

(defn model->response
  [{::group-specs/keys [id name currency]}]
  {:id           id
   :name         name
   :currency     currency})

(s/fdef model->response
  :args (s/cat :group ::group-specs/model)
  :ret ::group-specs/response)
