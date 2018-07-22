; Example of setting up CORS on a compojure API and ring-cors
; Note this is ripped out a of luminus project so some of the items
; like middleware are assuming a setup like that

(ns example.cors
  (:require
            [worldbuilder.routes.services :refer [service-routes]]
            [compojure.core :refer [routes wrap-routes]]
            [example.middleware :as middleware]
            [compojure.route :as route]
            [example.env :refer [defaults]]
            [mount.core :as mount]))

(def cors-headers
  { "Access-Control-Allow-Origin" "*"
    "Access-Control-Allow-Headers" "Content-Type"
    "Access-Control-Allow-Credentials" "true"
    "Access-Control-Allow-Methods" "GET,POST,OPTIONS" })

(defn all-cors
  "Allow requests from all origins"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response [:headers]
        merge cors-headers ))))

(mount/defstate app
  :start
  (middleware/wrap-base
    (routes
          (all-cors service-routes))))
