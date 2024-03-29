type point2D = float * float;;

let distance (point1 : point2D) (point2 : point2D) =
  let x1, y1 = point1 in
  let x2, y2 = point2 in
  sqrt(((y1 -. y2) ** 2.) +. (x1 -. x2) ** 2.)
;;

distance (0., 0.) (1., 1.);;
distance (2., 3.5) (4.2, 1.8);;
  







type pointN = float list

let rec distanceN point1 point2 =
  let square_diff a b = (a -. b) ** 2.0 in
  let rec sum_of_squares acc list1 list2 =
    match list1, list2 with
    | [], [] -> acc
    | x1::rest1, x2::rest2 -> sum_of_squares (acc +. square_diff x1 x2) rest1 rest2
    | _, _ -> invalid_arg "different dimensions"
  in
  sqrt (sum_of_squares 0.0 point1 point2)
;;



