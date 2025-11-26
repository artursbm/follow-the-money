(ns follow-the-money.domain.logic.user)

(defn calculate-due-amount-in-group [expenses]
  (reduce + 0 (map :amount expenses)))
