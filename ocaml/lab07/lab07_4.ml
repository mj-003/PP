module type CoordinateType = sig
  type t
  val add : t -> t -> t
  val create : float -> float -> float -> t
  val get_x : t -> float
  val get_y : t -> float
  val get_z : t -> float
end

module type Point3D = sig
  type t
  val create : float -> float -> float -> t
  val get_x : t -> float
  val get_y : t -> float
  val get_z : t -> float
end

module MakePoint (Coordinate : CoordinateType) : Point3D with type t = Coordinate.t = struct
  type t = Coordinate.t

  let create x y z = Coordinate.create x y z

  let get_x p = Coordinate.get_x p
  let get_y p = Coordinate.get_y p
  let get_z p = Coordinate.get_z p
end

module FloatCoordinate : CoordinateType with type t = float * float * float = struct
  type t = float * float * float
  let add (a, b, c) (x, y, z) = (a +. x, b +. y, c +. z)
  let create x y z = x, y, z

  let get_x (x, _, _) = x
  let get_y (_, y, _) = y
  let get_z (_, _, z) = z
end

module type TranslationVector = sig
  val x : float
  val y : float
  val z : float
end

module TranslatedPointVector : TranslationVector = struct
  let x = 2.0  
  let y = 3.0  
  let z = 4.0
end

module TranslatedPoint (Point : Point3D) (Vector : TranslationVector) : Point3D with type t = Point.t = struct
  type t = Point.t

  let create x y z = Point.create (x +. Vector.x) (y +. Vector.y) (z +. Vector.z)

  let get_x p = Point.get_x p
  let get_y p = Point.get_y p
  let get_z p = Point.get_z p
end

module type Segment = sig
  type point_type
  type t
  val create : point_type -> point_type -> t
  val start_point : t -> point_type
  val end_point : t -> point_type
end

module MakeSegment (Point : Point3D) : Segment with type point_type = Point.t = struct
  type point_type = Point.t
  type t = point_type * point_type

  let create start_p end_p = start_p, end_p

  let start_point (start_p, _) = start_p
  let end_point (_, end_p) = end_p
end

module TranslateSegment (S : Segment)(C : CoordinateType with type t = S.point_type) : Segment with type point_type = S.point_type = struct
  type point_type = S.point_type
  type t = S.t
  let create start_p end_p = S.create (C.add start_p (C.create 2.0 3.0 4.0)) (C.add end_p (C.create 2.0 3.0 4.0))
  let start_point s = S.start_point s
  let end_point s = S.end_point s
end

module MyPoint = MakePoint(FloatCoordinate)
module TranslatedMyPoint = TranslatedPoint(MyPoint)(TranslatedPointVector)
module MySegment = MakeSegment(MyPoint)
module Segment = TranslateSegment(MySegment)(FloatCoordinate)

let () =
  let module Point = TranslatedMyPoint in
  let p = Point.create 4.0 5.0 1.0 in
  Printf.printf "Translated Point: %.2f %.2f %.2f\n" (Point.get_x p) (Point.get_y p) (Point.get_z p);

  let module SegmentModule = MySegment in
  let translated = Segment.create (MyPoint.create 1.0 1.0 1.0) (MyPoint.create 3.0 3.0 3.0) in
  Printf.printf "Translated Start: %.2f %.2f %.2f\n" (MyPoint.get_x (Segment.start_point translated)) (MyPoint.get_y (Segment.start_point translated)) (MyPoint.get_z (Segment.start_point translated));
  Printf.printf "Translated End: %.2f %.2f %.2f\n" (MyPoint.get_x (Segment.end_point translated)) (MyPoint.get_y (Segment.end_point translated)) (MyPoint.get_z (Segment.end_point translated))
