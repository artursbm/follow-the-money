(ns follow-the-money.domain.models.user
  (:require
   [clojure.spec.alpha :as s]
   [clojure.spec.gen.alpha :as gen]))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(s/def ::email-type
  (s/with-gen
    (s/and string? #(re-matches email-regex %))
    #(gen/fmap (fn [n] (str "user" n "@example" (mod n 5) ".com"))
               (gen/int))))

(s/def ::id uuid?)
(s/def ::first-name string?)
(s/def ::last-name string?)
(s/def ::email ::email-type)
(s/def ::password string?)
(s/def ::created-at inst?)

(s/def ::request
  (s/keys :req-un [::first-name
                   ::last-name
                   ::email
                   ::password]))

(s/def ::response
  (s/keys :req-un [::id
                   ::first-name
                   ::last-name
                   ::email]))

(s/def ::model
  (s/keys :req [::first-name
                ::last-name
                ::email
                ::password]
          :opt [::id
                ::created-at]))

(s/def ::data
  (s/keys :req [::id
                ::first-name
                ::last-name
                ::email
                ::password
                ::created-at]))
