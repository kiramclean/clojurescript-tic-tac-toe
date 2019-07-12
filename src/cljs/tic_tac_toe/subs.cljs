(ns tic-tac-toe.subs
  (:require
    [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::layout
  (fn [db]
    (:layout db)))