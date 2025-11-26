(ns follow-the-money.application.service.group
  (:require
   [follow-the-money.domain.models.group :as group-specs]
   [follow-the-money.infrastructure.database.in-memory-db :as db]
   [clojure.spec.alpha :as s]))

(defn create-new-group
  "Handler Responsible for orchestrating the call from API.
   Adapts the request DTO to business model,
   validates groups data model, adds ID to group,
   saves group to database and adapts to response output."
  [group-model]
  (-> group-model
      (assoc ::group-specs/id (random-uuid)
             ::group-specs/created-at (java.time.Instant/now))
      db/insert-group))

(s/fdef create-new-group
  :args (s/cat :group ::group-specs/model)
  :ret ::group-specs/model)

