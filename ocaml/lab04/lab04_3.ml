type week_day = |Monday |Tuesday |Wednesday |Thursday |Friday |Saturday |Sunday

let to_string_day (day: week_day) = 
   match day with
   |Monday -> "Poniedzialek"
   |Tuesday -> "Wtorek"
   |Wednesday -> "Sroda"
   |Thursday -> "Czwartek"
   |Friday -> "Piatek"
   |Saturday -> "Sobota"
   |Sunday -> "Niedziela"
;;

let next_day (day: week_day) =
   match day with   
   |Monday -> Tuesday
   |Tuesday -> Wednesday
   |Wednesday -> Thursday
   |Thursday -> Friday
   |Friday -> Saturday
   |Saturday -> Sunday
   |Sunday -> Monday
;;

to_string_day (next_day Friday);;