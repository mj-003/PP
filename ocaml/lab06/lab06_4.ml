type 'a sequence = Cons of 'a * (unit -> 'a sequence)

let rec bell_from num =
  let rec stirling n m =
    if n = m then 1
    else m * stirling (n - 1) m + stirling n (m + 1)
  in
  let rec generate_bell n = Cons (stirling n 0, fun () -> generate_bell (n + 1))
  in generate_bell num

let rec from n = Cons(n, fun () -> from  (n + 1))


let stream_head (Cons (hd, _)) = hd
let stream_tail (Cons (_, tl_f)) = tl_f ()


let rec take n s =
  if n <= 0 then []
  else stream_head s :: take (n - 1) (stream_tail s)

let rec take_second n s =
  let temp = n * 2 in
  let rec take_second_helper temp s skip =
    if temp <= 0 then []
    else if skip then stream_head s :: take_second_helper (temp - 1) (stream_tail s) false
    else take_second_helper (temp - 1) (stream_tail s) true
  in take_second_helper temp s false

let rec skip n s = 
  if n = 0 then s
  else skip (n - 1) (stream_tail s)
  
let rec empty_stream () = Cons ((0, 0), fun () -> empty_stream ())
let rec pair_streams n s1 s2 =
  let rec pair_helper n s1 s2 =
    if n <= 0 then empty_stream()
    else Cons ((stream_head s1, stream_head s2), fun () -> 
       pair_helper (n - 1) (stream_tail s1) (stream_tail s2))
  in pair_helper n s1 s2

  
let rec map_stream f s =
  Cons (f (stream_head s), fun () -> map_stream f (stream_tail s))


let natural = from 0
let bell = bell_from 0
let bell_10 = take 10 (bell);;
let take_second_test = take_second 10 (bell);;
let skip_test = take 10 (skip 5 (bell))
let pair_streams_test = take 10 (pair_streams 10 (bell) (natural))



