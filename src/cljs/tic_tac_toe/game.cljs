(ns tic-tac-toe.game
  (:require [re-frame.core :as rf]
            [tic-tac-toe.events :as events]))

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

(defn handle-players-choice
  [db id]
  (if (get (:layout db) id)
    db
    (do
      (js/setTimeout #(rf/dispatch [::events/place-next-o]) 300)
      (assoc-in db [:layout id] "x"))))

