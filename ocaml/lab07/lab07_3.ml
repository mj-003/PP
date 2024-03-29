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


module MyPoint = MakePoint(FloatCoordinate)
module TranslatedMyPoint = TranslatedPoint(MyPoint)(TranslatedPointVector)


let () =
  let module Point = TranslatedMyPoint in
  let p = Point.create 4.0 5.0 1.0 in
  Printf.printf "%.2f %.2f %.2f" (Point.get_x p) (Point.get_y p) (Point.get_z p)



