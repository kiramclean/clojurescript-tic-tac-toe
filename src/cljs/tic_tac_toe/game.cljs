(ns tic-tac-toe.game)

(defn get-best-position
  [layout]
  (let [position (rand-int 9)]
    (if (get layout position)
      (recur layout)
      position)))

