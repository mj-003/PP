(* point *)
module type Point3DType = sig
  type point = { x: float; y: float; z: float }

  val create_point : float -> float -> float -> point
  val calculate_distance : point -> point -> float
end


module Point3D : Point3DType = struct
  type point = {x: float; y: float; z: float}

  let create_point x y z = {x; y; z}
  let  calculate_distance p1 p2 =
    let dx = p2.x -. p1.x in
    let dy = p2.y -. p1.y in
    let dz = p2.z -. p1.z in
  sqrt (dx *. dx +. dy *. dy +. dz *. dz)

end 

open Point3D
let p1 = create_point 0.0 0.0 0.0
let p2 = create_point 1.0 1.0 1.0
let result = calculate_distance p1 p2


(* segment *)
module type SegmentType = sig
  type segment = { start_point: Point3D.point; end_point: Point3D.point }

  val create_segment : Point3D.point -> Point3D.point -> segment
  val calculate_length : segment -> float
end


module Segment : SegmentType = struct
  type segment = { start_point: Point3D.point; end_point: Point3D.point }

  let create_segment start_point end_point = {start_point; end_point }

  let calculate_length segment =
    Point3D.calculate_distance segment.start_point segment.end_point
end

open Segment
let p1 = create_point 0. 0. 0.
let p2 = create_point 1. 1. 1.
let mySegment = create_segment p1 p2
let result = calculate_length mySegment
