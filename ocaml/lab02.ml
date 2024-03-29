(* zad1 - ostatni element*)
let rec lastOne list =
  match list with
 |[] -> None
 |[x] -> Some x
 |_::tail -> lastOne tail
;;
lastOne [2;3;4;5;6];;


(*zad2 - dwa ostatnie elementy*)
let rec twoLastOnes list =
  match list with
 |[] | [_] -> None
 |[x; y] -> Some (x, y)
 |_ :: tail -> twoLastOnes tail
;;
twoLastOnes [2;3;4;5;6];;


(* zad3 - dlugosc listy*)
let length list =
  let rec insideLen len myList =
  match myList with
    |[] -> len
    |_ :: tail -> insideLen (len + 1) tail
  in 
insideLen 0 list
;;
length [2;3;4;5;6];;

let rec listLength list =
  match list with
  |[] -> 0
  |_::tail -> 1 + listLength tail
;;
listLength [2;3;4;5;6];;


(* zad4 - lista w odwrotnej kolejnosci*)
let revers list =
  let rec insideRev return myList =
    match myList with
    |[] -> return
    |head :: tail -> insideRev (head :: return) tail
  in
insideRev [] list
;;
revers [2;3;4;5];;


(* zad5 - sprawdzanie czy palindrom*)
let isPalindrome myString =
  let len = String. length myString in
  let rec checkIfPalindrome left right =
    if left >= right then true
    else if myString.[left] = myString.[right] then checkIfPalindrome (left + 1) (right - 1)
    else false
  in
  if len <= 1 then true  
  else checkIfPalindrome 0 (len - 1)
;;
isPalindrome "abbac";;
isPalindrome "abba";;


(* zad6 - usuwanie duplikatow*)
let rec compress list =
  let rec contains myList element =
   match myList with
   |[] -> false
   |head :: tail -> if head = element then true else contains tail element
  in 
  match list with
  |[] -> []
  |head :: tail -> if contains tail head then compress tail else head :: compress tail
;;
compress[2;3;4;4;3;2;1;5;1];;


(* zad7 - parzyste indeksy*)
let evenIndex list =
  let rec getEvenIndex myList index result =
    match myList with
    | [] -> result
    | head :: tail ->
      if index mod 2 = 0 then
        getEvenIndex tail (index + 1) (result @ [head])
      else
        getEvenIndex tail (index + 1) result
  in
  getEvenIndex list 0 []
;;
evenIndex [1;2;3;4;5;6];;


(* zad8 - liczba pierwsza*)
let isPrime num =
  let rec isNotDivisor div =
    if (div * div > num) then true
    else if (num mod div = 0) then false
    else isNotDivisor (div + 1)
  in
  num >= 2 && isNotDivisor 2
;;
isPrime 5;;
isPrime 16;;

  





    


  