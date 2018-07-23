; Adds a new user if the password or email is unique
; This probably isn't the best way to do this, but it works
; Probably best to allow mongo to check and just handle the unique error
; Or to combine it into a single query
; Monogo Operations included here for example
(ns example.user.create
  (:require [example.db.core :as db]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all])


; The following two functions should live in a db namespace
; but are incldued here for example purposes
(defn get-user-count-username
  [username]
  (mc/count db "user" {:username username}))

(defn get-user-count-email
  [email]
  (mc/count db "user" {:email email}))

(defn create
  "Checks to make sure the password and Username are unique"
  [username email password]
  (if (and (= 0 (get-user-count-username username)) (= 0 (get-user-count-email email)))
    (db/create-user username email password) ;This would need to be created in the db namespace
    {:body "The Username Or Email Is already taken"}))
