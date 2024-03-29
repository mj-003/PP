let rec stirling n m =
  match (n, m) with
  | _, _ when m = 1 || n = m -> 1
  | _, _ when m > n -> 0
  | _, _ -> stirling (n - 1) (m - 1) + m * stirling (n - 1) m
;;

let lazy_stirling = lazy (stirling 5 2);;
let hello = "Hello"
let result = Lazy.force lazy_stirling;; 