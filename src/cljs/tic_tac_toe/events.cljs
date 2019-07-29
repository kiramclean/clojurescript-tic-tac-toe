(ns tic-tac-toe.events
  (:require
    [re-frame.core :as rf]
    [tic-tac-toe.db :as db]
    [tic-tac-toe.game :as game]
    [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
    ))

(rf/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(rf/reg-event-db
  ::place-next-o
  (fn [db _]
    (if (game/game-over (:layout db))
      db
      (let [position (game/get-best-position (:layout db))]
        (if position
          (assoc-in db [:layout (game/get-best-position (:layout db))] "o")
          db)))))

(rf/reg-event-db
  ::choose-square
  (fn [db [_ id]]
    (if (get (:layout db) id)
      db
      (do
        (js/setTimeout #(rf/dispatch [::place-next-o]) 250)
        (assoc-in db [:layout id] "x")))))