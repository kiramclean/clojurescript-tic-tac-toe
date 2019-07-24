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

(defn board-full
  [layout]
  (->> layout
       get-empty-squares
       count
       (= 0)))

(defn game-over
  [layout]
  (or (board-full layout) (get-winner layout)))

(def winning-lines [[0 1 2]
                    [3 4 5]
                    [6 7 8]
                    [0 3 6]
                    [1 4 7]
                    [2 5 8]
                    [0 4 8]
                    [2 4 6]])

(defn line-winner
  [layout [pos1 pos2 pos3]]
  (let [piece (get layout pos1)]
    (when (-> piece
              (and (= piece (get layout pos2)))
              (and (= piece (get layout pos3))))
      piece)))

(defn check-for-winner
  [layout lines-to-check]
  (when (not (empty? lines-to-check))
    (let [winner (line-winner layout (first lines-to-check))]
      (if winner
        winner
        (recur layout (rest lines-to-check))))))

(defn get-winner
  ([layout] (check-for-winner layout winning-lines))
  ([layout lines-to-check] (check-for-winner layout lines-to-check)))

