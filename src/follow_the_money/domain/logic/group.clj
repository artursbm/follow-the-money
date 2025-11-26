(ns follow-the-money.domain.logic.group)

(defn update-total-balance
  [group expense]
  (update group :total-amount + (:amount expense)))
