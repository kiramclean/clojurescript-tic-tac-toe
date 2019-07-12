(ns tic-tac-toe.events
  (:require
    [re-frame.core :as rf]
    [tic-tac-toe.db :as db]
    [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
    ))

(rf/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(rf/reg-event-db
  ::choose-square
  (fn [db [_ id]]
    (assoc-in db [:layout id] "x")))