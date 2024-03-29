type solid_figure =
  | Cuboid of float * float * float  
  | Cone of float * float           
  | Sphere of float                
  | Cylinder of float * float       


let volume : solid_figure -> float = 
  let pi = 4. *. atan 1.0 in
  function
  | Cuboid (length, width, height) -> length *. width *. height
  | Cone (radius, height) -> (pi *. radius *. radius *. height) /. 3.0
  | Sphere radius -> (4.0 *. pi *. radius *. radius *. radius) /. 3.0
  | Cylinder (radius, height) -> pi *. radius *. radius *. height







type solid_figure2 =
  | Cuboid of {a : float; b : float; h : float}
  | Cone of {h : float; r : float}
  | Sphere of {r : float}
  | Cylinder of {r : float; h : float}

let volumeOfFigure (figure : solid_figure2) = 
   let pi = 4. *. atan 1.0 in
   function
   | Cuboid {a; b; h} -> a *. b *. h
   | Cone {h; r} -> pi *. (1. /. 3.) *. (r ** 2.)
   | Sphere {r} -> (4. /. 3.) *. (r ** 3.) *. pi
   | Cylinder {r; h} -> pi *. (r ** 2.) *. h