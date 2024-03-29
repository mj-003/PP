import scala.annotation.targetName

// ----- zadanie 1, 4 -----

enum Result[+X]:
  case Success(x: X)
  case Failure(msg: String)

  @targetName("map")
  def >>= [Y](f: X => Result[Y]): Result[Y] = this match {
    case Result.Success(x) => f(x)
    case Result.Failure(msg) => Result.Failure(msg)
  }


// ----- zadanie 2 -----

def intInput (s: String): Result[Int] = {
  try {
    Result.Success(s.toInt)
  } catch {
    case _: NumberFormatException => Result.Failure("Not a number")
  }
}

def calcSqrt (x: Int): Result[Int] = {
  if (x >= 0) {
    Result.Success(Math.sqrt(x).toInt)
  } else {
    Result.Failure("Negative number")
  }
}

def takeLetter (index: Int): Result[String] = {
  if (index >= 0 && index < 26) {
    Result.Success(('a' + index).toChar.toString)
  } else {
    Result.Failure("Index out of bounds")
  }
}

val intInputResult = intInput("25");

val calcSqrResult = intInputResult match {
  case Result.Success(i) => calcSqrt(i)
  case Result.Failure(msg) => Result.Failure(msg)
}

val takeLetterResult = calcSqrResult match {
  case Result.Success(i) => takeLetter(i)
  case Result.Failure(msg) => Result.Failure(msg)
}


// ----- zadanie 3 -----

def bind[X, Y] (x: Result[X], f: X => Result[Y]): Result[Y] = x match {
  case Result.Success(i) => f(i)
  case Result.Failure(msg) => Result.Failure(msg)
}

val result1 = intInput("25") >>= calcSqrt >>= takeLetter;
val result2 = intInput("-5") >>= calcSqrt >>= takeLetter;
val result3 = intInput("2500") >>= calcSqrt >>= takeLetter;
val result4 = intInput("a") >>= calcSqrt >>= takeLetter;






