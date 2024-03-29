(* ***** ZADANIE 0 ***** *)
let log prefix = 
  fun dateTime -> 
     fun text -> 
     "[" ^ String.uppercase_ascii prefix ^ "] " ^ dateTime ^ " " ^ text;;

let warnLog = log "WARN";;
let nightlyWarnLog = warnLog "2022-10-26 01:45";;
let result1 = nightlyWarnLog "Hello";;
let result2 = log "Warn" "2022-10-26 01:45" "Hello";;


(* ***** ZADANIE 1 ***** *)
let rec myMap list f =
  match list with
  |[] -> []
  |hd :: tl -> (f hd) :: myMap tl f
;;

myMap [2;3;4;5] (fun x -> x * 4);;
myMap [5;6;7] (fun float(x) -> x *. 4.);;


(* ***** ZADANIE 2 ***** *)
let rec myFilter list pred =
  match list with 
  |[] -> []
  |hd :: tl -> if pred hd then hd :: myFilter tl pred
  else myFilter tl pred
;;

myFilter [2;3;4;5] (fun x -> x mod 2 = 0);;
myFilter [4;5;6;7] (fun x -> x mod 2 <> 0);;


(* ***** ZADANIE 3 ***** *)
let rec myReduce list op acc =
  match list with
  |[] -> acc
  |hd :: tl -> myReduce tl op (op hd acc)
;;

myReduce [2;3;4] (+) 0;;
myReduce [5;6;7] ( * ) 1;;

let rec myReduceTwo op list acc = 
  match list with
  |[] -> acc
  |hd :: tl -> op hd (myReduceTwo op tl acc)
;;


(* ***** ZADANIE 4 ***** *)
let average list =
  float(List.fold_left (+) 0 list) /. float(List.length list);;

average [2;3;5];;
average [3;3;3];;


(* ***** ZADANIE 5 ***** *)
let acronym text =
  let words = String.split_on_char ' ' text in
    List.fold_left (fun acc word -> acc ^ if word = "" then "" else String.make 1 word.[0]) "" words
;;

acronym "Narodowy Fundusz Zdrowia";;
acronym "Zaklad Ubezpieczen Spolecznych";;
acronym "";;


(* ***** ZADANIE 6 ***** *)
let sqrList list =
  let sum = List.fold_left (+) 0 list in
    List.map (fun x -> x * x) (List.filter (fun x -> x * x * x < sum) list);;

sqrList [1;2;3;4;5];;
sqrList [];;



