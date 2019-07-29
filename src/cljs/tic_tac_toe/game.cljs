(ns tic-tac-toe.game)

(def winning-lines [[0 1 2]
                    [3 4 5]
                    [6 7 8]
                    [0 3 6]
                    [1 4 7]
                    [2 5 8]
                    [0 4 8]
                    [2 4 6]])

(defn- line-won-by
  [layout [pos1 pos2 pos3]]
  (let [piece (get layout pos1)]
    (when (-> piece
              (and (= piece (get layout pos2)))
              (and (= piece (get layout pos3))))
      piece)))

(defn- check-for-winner
  [layout lines-to-check]
  (when (not (empty? lines-to-check))
    (let [winner (line-won-by layout (first lines-to-check))]
      (if winner
        winner
        (recur layout (rest lines-to-check))))))

(defn- get-empty-square-ids
  [layout]
  (->> layout
       (filter #(not (second %)))
       (into {})
       keys))

(defn- board-full?
  [layout]
  (->> layout
       get-empty-square-ids
       empty?))

(defn- get-new-layout
  [layout position player]
  (assoc layout position player))

(defn get-winner
  ([layout] (check-for-winner layout winning-lines))
  ([layout lines-to-check] (check-for-winner layout lines-to-check)))

(defn game-over
  [layout]
  (or (board-full? layout) (get-winner layout)))

(defn- get-opponent
  [player]
  (if (= "x" player) "o" "x"))

(defn- get-score
  [winner current-player score]
  (cond
    (= winner current-player) score
    (= winner (get-opponent current-player)) (* -1 score)
    :else 0))

(declare get-best-position)

(defn- simulate-next-move
  [layout player]
  (get-new-layout layout
                  (get-best-position layout player)
                  player))

(defn- score-move
  [layout current-player score]
  (if (game-over layout)
    (get-score (get-winner layout) current-player score)
    (recur (simulate-next-move layout (get-opponent current-player))
           (get-opponent current-player)
           (* -1 score))))

(def score-move (memoize score-move))

(defn- get-scores
  [layout square-ids player]
  (map #(score-move (get-new-layout layout % player)
                    player
                    10)
       square-ids))

(defn- score-positions
  [player layout]
  (let [empty-square-ids (get-empty-square-ids layout)]
    (zipmap empty-square-ids (get-scores layout empty-square-ids player))))

(defn get-best-position
  ([layout]
   (->> layout
        (score-positions "o")
        (sort-by val >)
        first
        key))
  ([layout player]
   (->> layout
        (score-positions player)
        (sort-by val >)
        first
        key)))

;(defn get-best-position
;  [layout]
;  (rand-nth (get-empty-squares layout)))
