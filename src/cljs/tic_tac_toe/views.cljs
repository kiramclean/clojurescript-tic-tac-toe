(ns tic-tac-toe.views
  (:require
    [re-frame.core :as rf]
    [tic-tac-toe.subs :as subs]
    [tic-tac-toe.events :as events]
    ))

(defn square
  [[id value]]
  [:div.square {:id       id
                :on-click #(rf/dispatch [::events/choose-square id])} value])
(defn board
  []
  (let [layout @(rf/subscribe [::subs/layout])]
    [:div.board
     (for [[position value] layout]
       ^{:key position} [square [position value]])
     ]))

(defn ui []
  [board])
