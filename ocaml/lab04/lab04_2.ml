type person = {
  first_name : string;
  second_name : string;
  age : int;
  gender : string;
  shoe_size : int;
}
;;

type partnership = {
   person1 : person;
   person2 : person
}

let younger_partner (partners : partnership) =
  if partners.person1.age <= partners.person2.age then
    partners.person1
  else
    partners.person2
;;

let p1 = {
   first_name = "John";
   second_name = "Doe";
   age = 30;
   gender = "Men";
   shoe_size = 43;
}

let p2 = {
   first_name = "Ann";
   second_name = "Mari";
   age = 18;
   gender = "Women";
   shoe_size = 38;
}

let partners = {
person1 = p1;
person2 = p2;
};;

younger_partner partners;;



type person_tuple = string * string * int * string * int;;

type partnership_tuple = person_tuple * person_tuple;;

let younger_person_tuple (partners : partnership_tuple) = 
   match partners with
   (partner1, partner2) ->
      let (_,_,age1,_,_) = partner1 in
      let (_, _, age2, _, _) = partner2 in
   if age1 <= age2 then partner1 else partner2
;;

younger_person_tuple (("John", "Doe", 40, "male", 43), ("Ann", "Mari", 18, "female", 38));;
