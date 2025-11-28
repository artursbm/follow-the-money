(ns follow-the-money.application.adapters.user 
  (:require
   [clojure.spec.alpha :as s]
   [follow-the-money.domain.models.user :as user-specs]))


(defn request->model
  [{:keys [first-name last-name email password]}]
  {::user-specs/first-name first-name
   ::user-specs/last-name  last-name
   ::user-specs/email      email
   ::user-specs/password   password})

(s/fdef request->model
  :args (s/cat :user ::user-specs/request)
  :ret ::user-specs/model)

(defn model->data
  [{::user-specs/keys [id first-name last-name email password created-at]}]
  {::user-specs/id         id
   ::user-specs/first-name first-name
   ::user-specs/last-name  last-name
   ::user-specs/email      email
   ::user-specs/password   password
   ::user-specs/created-at created-at})

(s/fdef model->data
  :args (s/cat :user ::user-specs/model)
  :ret ::user-specs/data)

(defn data->model
  [{::user-specs/keys [id first-name last-name email password created-at]}]
  {::user-specs/id         id
   ::user-specs/first-name first-name
   ::user-specs/last-name  last-name
   ::user-specs/email      email
   ::user-specs/password   password
   ::user-specs/created-at created-at})

(s/fdef data->model
  :args (s/cat :user ::user-specs/data)
  :ret ::user-specs/model)

(defn model->response
  [{::user-specs/keys [id first-name last-name email]}]
  {:id         id
   :first-name first-name
   :last-name  last-name
   :email      email})

(s/fdef model->response
  :args (s/cat :user ::user-specs/model)
  :ret ::user-specs/response)
