(ns follow-the-money.infrastructure.utils.misc)

(defn qualify-key
  [base-ns entity]
  (keyword base-ns (name entity)))

(defn unqualify-key
  [entity]
  (keyword (name entity)))
