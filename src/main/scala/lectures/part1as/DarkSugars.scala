package lectures.part1as

/**
  * Created by cristian on 20/10/2018
  */
object DarkSugars extends App {

  // syntax sugar #1: methods with single params
  def singleArdMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArdMethod {
    // write some code
    42
  }
  println(description)

  // syntax sugar #2: single abstract method
  trait Action {
    def Act(i: Int): Int
  }

  val anInstance: Action = new Action {
    override def Act(i: Int): Int = i + 1
  }

  val aFunkyInstance: Action = (x: Int) => (x + 1)  // magic

  // example: Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello Scala")
  })
  // better
  val aSweeterThread = new Thread(() => println("sweet Scala!"))

  // syntax sugar #3: the :: and #:: methods are special
  val prependedList = 2 :: List(3, 4)
  println(prependedList)


  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this // actual implementation here
  }
  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // syntax sugar #4: multi-word method naming
  class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
  }
  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet!"

  // syntax sugar #5: infix types
  class Composite[A, B]
  val composite: Int Composite String = ???

  class -->[A, B]
  val towards: Int --> String = ???

  // syntax sugar #6: update() is very special, much like apply()
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // rewritten by compiler as anArray.update(2, 7)
  // used in mutable collections

}
