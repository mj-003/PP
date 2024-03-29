let rec stirling n m =
  match (n, m) with
  | _, _ when m = 1 || n = m -> 1
  | _, _ when m > n -> 0
  | _, _ -> stirling (n - 1) (m - 1) + m * stirling (n - 1) m
;;

let memoized_stirling n m =
  let cache = Hashtbl.create (n) in
  let rec stirling n m =
    match Hashtbl.find_opt cache (n, m) with
    | Some result -> result
    | None ->
      match (n, m) with
      | _, _ when m = 1 || n = m -> 1
      | _, _ when m > n -> 0
      | _,_ -> let result = stirling (n - 1) (m - 1) + m * stirling (n - 1) m 
    in Hashtbl.add cache (n, m) result; 
    result
  in
  stirling n m
;;

let result = memoized_stirling 100 10;;










