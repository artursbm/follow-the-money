(ns follow-the-money.infrastructure.database.in-memory-db
  (:require
   [follow-the-money.application.adapters.group :as adapters.group]
   [follow-the-money.application.adapters.user :as adapters.user]
   [follow-the-money.domain.models.group :as group-specs]))

(def database (atom {:group {}
                     :user {}
                     :expense {}
                     :payment {}}))

(defn insert-group
  [group-model]
  (let [id-key     (keyword (str (::group-specs/id group-model)))
        group-data (adapters.group/model->data group-model)]
    (-> database
        (swap! update-in [:group] assoc id-key group-data)
        :group
        (get id-key)
        (adapters.group/data->model))))

(defn insert-user
  [user-model]
  (let [id-key     (keyword (str (::group-specs/id user-model)))
        user-data (adapters.user/model->data user-model)]
    (-> database
        (swap! update-in [:user] assoc id-key user-data)
        :user
        (get id-key)
        (adapters.user/data->model))))
