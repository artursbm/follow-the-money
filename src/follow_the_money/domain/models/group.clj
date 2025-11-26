(ns follow-the-money.domain.models.group
  (:require
   [clojure.spec.alpha :as s]))

(s/def ::id uuid?)
(s/def ::name string?)
(s/def ::currency #{:currency/real :currency/euro :currency/us-dollar :currency/pound})
(s/def ::created-at inst?)

(s/def ::request
  (s/keys :req-un [::name
                   ::currency]))

(s/def ::response
  (s/keys :req-un [::id
                   ::name
                   ::currency
                   ::created-at]))

(s/def ::model
  (s/keys :req [::name
                ::currency]
          :opt [::id
                ::created-at]))

(s/def ::data
  (s/keys :req [::id
                ::name
                ::currency
                ::created-at]))
