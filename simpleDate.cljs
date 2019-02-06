; Simple example of formatting a date using Javascript and no libraries
; does mm/dd/yyyy
; pull from project where the date is a MySQL timestamp, but should work whever the native
; Javascript Date can parse correclty



(let [itemDate (js/Date. date)]
  (str (+ (.getMonth itemDate) 1) "/" (.getDate itemDate) "/" (.getFullYear itemDate)))
