package lectures.part2afp

/**
  * Created by cristian on 21/10/2018
  */
object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // Function1[Int, Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableError

  class FunctionNotApplicableError extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  // {1, 2, 5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(2))

  // PF Utilities
  println(aPartialFunction.isDefinedAt(567))

  // lift to total function
  val lifted = aPartialFunction.lift  // Int => Option[Int]
  println(lifted(2))
  println(lifted(98))

  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 46 => 98
  }
  println(pfChain(46))

  // PF extend total functions
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial functions as well
  val aMappedList = List(1, 2, 3).map {
    case 1 => 42
    case 2 => 56
    case 3 => 999
  }
  println(aMappedList)

  /*
      NOTE: PF can only have one parameter type
   */

  /**
    *   Exercises
    *
    *   1 - Construnct a PF instance yourself (anonymous class)
    *   2 - Dumb chatbot as a partial function
    */

    // 1.
  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(v1: Int): Int = v1 match {
      case 1 => 42
      case 2 => 56
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5
  }

  // 2.
  val chatBot: PartialFunction[String, String] = {
    case "hello" => "Hi, I am chatbot"
    case "goodbye" => "Once upon a time..."
    case "call mom" => "I can't, I don't have a phone"
    case _ => "Sorry?"
  }
  scala.io.Source.stdin.getLines().foreach(line => println("chatbot says: " + chatBot(line)))

}
