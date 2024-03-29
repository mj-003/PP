let memo_rec f =
  let cache = Hashtbl.create 10 in
  let rec g elem =
    try Hashtbl.find cache elem 
    with Not_found ->
      let result = f g elem in
      Hashtbl.add cache elem result;
      result
  in
  g
let fib self = function
  | 0 -> 1
  | 1 -> 1
  | n -> self (n - 1) + self (n - 2)

let fib_memoized = memo_rec fib 10