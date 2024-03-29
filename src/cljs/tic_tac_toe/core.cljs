(ns tic-tac-toe.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [tic-tac-toe.events :as events]
   [tic-tac-toe.views :as views]
   [tic-tac-toe.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/ui]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
