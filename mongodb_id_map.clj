; simple function to convert the mongoDB generated ID when using a map
; to a string. Seems to be needed in many cases or JSON parse fails
; Note this is pulled out of luminius so stuff like mc/ is assumed
; to be reference to Monger library call

(defn get-entity-by-world
  "Gets all entities of 'entityType' for world 'worldId'"
  [entityType worldId]
  (let [entities (mc/find-maps db entityType {:worldId worldId})]
    (map ; Turn characters into a modified list
      #(update % :_id str) ; By updating each map :id by casting to a string
      entities)))
