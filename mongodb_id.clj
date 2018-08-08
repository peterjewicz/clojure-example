; simple function to convert the mongoDB generated ID
; to a string. Seems to be needed in many cases or it
; JSON parsing fails

(defn format-id
  "Takes an entity and returns it with the :_converted to a string"
  [entity]
  (assoc user :_id (str (entity :_id))))
