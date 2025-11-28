(ns follow-the-money.application.service.user
  (:require
   [clojure.spec.alpha :as s]
   [follow-the-money.domain.models.user :as user-specs]
   [follow-the-money.infrastructure.database.in-memory-db :as db]))

(defn create-new-user
  "Handler Responsible for orchestrating the call from API.
   Adapts the request DTO to business model,
   validates user data model, adds ID and created-at to user,
   saves it to database and adapts to response output."
  [user-model]
  (-> user-model
      (assoc ::user-specs/id (random-uuid)
             ::user-specs/created-at (java.time.Instant/now))
      db/insert-user))

(s/fdef create-new-user
  :args (s/cat :user ::user-specs/model)
  :ret ::user-specs/model)

