// zad1 - ostatni element
def lastElement[A] (list: List[A]): Option[A] = list match {
    case Nil => None
    case x :: Nil => Some(x)
    case _ :: tail => lastElement(tail)
  }
lastElement(List(2,3,4))
lastElement(List(2,3,4,5))


// zad2 - dwa ostatnie elementy
def twoLastElements[A] (list: List[A]): Option[(A, A)] = list match {
  case Nil => None
  case x :: Nil => None
  case x :: y :: Nil => Some(x, y)
  case _ :: tail => twoLastElements(tail)
}
twoLastElements(List(2,4,5,6))
twoLastElements(List())


// zad3 - dlugosc listy
def listLength[A] (list: List[A]): Int = {
  def insideLength(myList: List[A], len: Int): Int = myList match {
    case Nil => 0
    case head :: tail => insideLength(tail, len + 1)
  }
  insideLength(list, 0)
}
listLength(List(2,3,4,5,6,7,8))
listLength(List(2,3,4))


// zad4 - odwrocenie listy
def reverse[A](list: List[A]): List[A] = {
  def insideRev(myList: List[A], result: List[A]): List[A] = myList match {
    case Nil => result
    case head :: tail => insideRev(tail, head :: result)
  }
  insideRev(list, Nil)
}
reverse(List('a','b','c','d'))
reverse(List('a'))


// zad5 - czy palindrom
def isPalindrome(myString: String): Boolean = {
  val len = myString.length
  def checkIfPalindrome(lIndex: Int, rIndex: Int): Boolean = {
    (lIndex, rIndex) match {
      case (left, right) if left >= right => true
      case (left, right) if myString(left) == myString(right) => checkIfPalindrome(left + 1, right - 1)
      case _ => false
    }
  }
  checkIfPalindrome(0, len - 1)
}
isPalindrome("abba")
isPalindrome("abbac")


// zad6 - usunac duplikaty
def compress[A](list: List[A]): List[A] = {
  def contains(myList: List[A], element: A): Boolean = myList match {
    case Nil => false
    case head :: tail => if head == element then true else contains(tail, element)
  }
  list match
    case Nil => Nil
    case head :: tail => if contains(tail, head) then compress(tail) else head :: compress(tail)
}
compress(List(1,1,1,2,2,2,3,3,3,4,4,4))
compress(List('a','b','a','a','b','b'))


// zad7 - parzyste indeksy
def evenIndex[A](list: List[A]): List[A] = {
  def getEvenIndex(myList: List[A], index: Int, result: List[A]): List[A] = myList match {
    case Nil => result
    case head :: tail =>
      if (index % 2 == 0) {
        getEvenIndex(tail, index + 1, result :+ head)
      } else {
        getEvenIndex(tail, index + 1, result)
      }
  }
  getEvenIndex(list, 0, Nil)
}
evenIndex(List(1,2,3,4,5,6,7,8))
evenIndex(List('a','b','c','d'))


// zad8 - czy pierwsza
def isPrime(num: Int): Boolean = {
  def isNotDivisor(div: Int): Boolean = {
    if (div * div > num) true
    else if (num % div == 0) false
    else isNotDivisor(div + 1)
  }
  if (num <= 1) false
  else isNotDivisor(2)
}
isPrime(4)
isPrime(5)

def flatten(list: List[List[Int]]): List[Int] = {
  def aux(acc: List[Int], lists: List[List[Int]]): List[Int] = lists match {
    case Nil => acc
    case (hd :: tl) :: rest => aux(hd :: acc, tl :: rest)
    case Nil :: rest => aux(acc, rest)
  }
  val flattened = aux(Nil, list)
  def reverse(acc: List[Int], remaining: List[Int]): List[Int] = remaining match {
    case Nil => acc
    case hd :: tl => reverse(hd :: acc, tl)
  }
  reverse(Nil, flattened)
}

val exampleList = List(List(1, 5, 3, 8), List(2, 7, 4, 1), Nil, List(9))
val result = flatten(exampleList)














