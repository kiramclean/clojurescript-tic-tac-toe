(ns tic-tac-toe.views
  (:require
    [re-frame.core :as rf]
    [tic-tac-toe.subs :as subs]
    [tic-tac-toe.events :as events]
    [tic-tac-toe.game :as game]
    ))

(defn square
  [[id value]]
  [:div.square {:id       id
                :on-click #(rf/dispatch [::events/choose-square id])} value])
(defn board
  []
  (let [layout @(rf/subscribe [::subs/layout])]
    [:div.board {:class (when (game/game-over layout) "game-over")}
     (for [[position value] layout]
       ^{:key position} [square [position value]])
     ]))

(defn reset
  []
  (let [layout @(rf/subscribe [::subs/layout])]
    (when (game/game-over layout)
      [:div.new-game
       [:button {:on-click #(rf/dispatch [::events/initialize-db])}
        "New game"]])))

(defn message
  []
  (let [layout @(rf/subscribe [::subs/layout])
        winner (game/get-winner layout)
        game-over (game/game-over layout)]
    (if game-over
      [:div.message (if winner
                      (str winner " wins!")
                      "Tie!")])))

(defn ui []
  [:div
   [board]
   [message]
   [reset]])
