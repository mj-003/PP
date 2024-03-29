module type Binary_Tree_Type = sig 
  type 'a tree = Empty | Node of 'a tree * 'a * 'a tree
  val add_leaf : 'a -> 'a tree -> 'a tree
  val delete_leaf : 'a -> 'a tree -> 'a tree
  val in_order : 'a tree -> 'a list
  val pre_order : 'a tree -> 'a list
  val post_order : 'a tree -> 'a list
  val return_leaves : 'a tree -> 'a list
  val pre_ord' : 'a tree -> 'a list
end


module Binary_tree : Binary_Tree_Type = struct
  type 'a tree = Empty | Node of 'a tree * 'a * 'a tree

  let rec add_leaf value = function
  | Empty -> Node (Empty, value, Empty)
  | Node (left, node_value, right) ->
      if value < node_value then Node (add_leaf value left, node_value, right)
      else Node (left, node_value, add_leaf value right)

  let rec min_value = function
    | Empty -> failwith "Empty tree"
    | Node (Empty, value, _) -> value
    | Node (left, _, _) -> min_value left

  let rec delete_leaf value = function
    | Empty -> Empty
    | Node (left, nodeValue, right) when value < nodeValue ->
          Node((delete_leaf value left), nodeValue, right)
    | Node (left, nodeValue, right) when value > nodeValue ->
          Node(left, nodeValue, (delete_leaf value right))
    | Node (Empty, _, right) -> right
    | Node (left, _, Empty) -> left
    | Node (left, _, right) ->
          let newValue = min_value right in
          Node(left, newValue, (delete_leaf newValue right))

  let rec in_order = function
    | Empty -> []
    | Node (left, value, right) -> (in_order left) @ [value] @ (in_order right)

  let rec pre_order = function
    | Empty -> []
    | Node (left, value, right) -> [value] @ (pre_order left) @ (pre_order right)

  let rec post_order = function
    | Empty -> []
    | Node (left, value, right) -> (post_order left) @ (post_order right) @ [value]

  let rec return_leaves = function
    | Empty -> []
    | Node (Empty, value, Empty) -> [value]
    | Node (left, _, right) -> (return_leaves left) @ (return_leaves right)

  let pre_ord' t =
    let rec preord (tree, labels) =
      match tree with
      | Empty -> labels
      | Node (t1, v, t2) -> v :: preord (t1, preord (t2, labels))
    in
    preord (t, [])
    
end

open Binary_tree

let myTree = ref (Node (Node (Empty, 1, Empty), 2, Node (Empty, 3, Empty)))
let _ = myTree := add_leaf 4 !myTree
let _ = myTree := delete_leaf 2 !myTree
let in_test = in_order !myTree
let pre_test = pre_order !myTree
let pre_test' = pre_ord' !myTree
let post_test = post_order !myTree
let leaves_test = return_leaves !myTree
