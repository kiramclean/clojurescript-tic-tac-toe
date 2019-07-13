(ns tic-tac-toe.game)

(defn get-empty-squares
  [layout]
  (->> layout
       (filter #(not (second %)))
       (into {})
       keys))

(defn get-best-position
  [layout]
  (rand-nth (get-empty-squares layout)))

(defn game-over
  [layout]
  (->> layout
       get-empty-squares
       count
       (= 0)))

