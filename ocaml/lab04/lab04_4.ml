type 'a maybe =
  | Nothing
  | Just of 'a
;;

let safeHead list =
  match list with
  | [] -> Nothing
  | head :: _ -> Just head
;;

let myList1 = [2;3;4;5;6]
let myList2 = []
let myList3 = ["ala";"jest";"brudna"];;

let res1 = safeHead myList1
let res2 = safeHead myList2
let res3 = safeHead myList3
