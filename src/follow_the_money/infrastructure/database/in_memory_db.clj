(ns follow-the-money.infrastructure.database.in-memory-db
  (:require
   [follow-the-money.application.adapters.group :as adapters.group]
   [follow-the-money.domain.models.group :as group-specs]))

(def database (atom {:group {}
                     :user {}
                     :expense {}
                     :payment {}}))

(defn insert-group
  [group-model]
  (let [id-key     (keyword (str (::group-specs/id group-model)))
        group-data (adapters.group/model->group-data group-model)]
    (-> database
        (swap! update-in [:group] assoc id-key group-data)
        :group
        (get id-key)
        (adapters.group/group-data->model))))

(defn insert-user
  [user-data]
  (->> user-data
       (swap! database update-in [:user] conj)
       :user))

(defn insert-expense
  [expense-data]
  (->> expense-data
       (swap! database update-in [:expense] conj)
       :expense))

(defn insert-payment
  [payment-data]
  (->> payment-data
       (swap! database update-in [:payment] conj)
       :payment))
