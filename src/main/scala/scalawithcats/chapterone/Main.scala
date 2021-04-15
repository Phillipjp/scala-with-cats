package scalawithcats.chapterone

import scalawithcats.chapterone.PrintableInstances._
import scalawithcats.chapterone.PrintableSyntax.PrintableOps
import cats._
import cats.implicits.catsSyntaxEq
import cats.syntax.show._
object Main {

  def main(args: Array[String]): Unit = {
    val cat = Cat("Garfield", 41, "ginger and black")
    Printable.print(cat)
    cat.print

    implicit val catShow: Show[Cat] = Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat.")
    println(cat.show)

    val cat1 = Cat("Garfield",   38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    implicit val catEq = Eq.instance[Cat] { (cat1, cat2) =>
      cat1.name === cat2.name &&
      cat1.age === cat2.age &&
      cat1.color === cat2. color
    }

    println(cat1 === cat1)
    println(cat1 === cat2)
    println(cat1 =!= cat2)
    println(optionCat1 === optionCat1)
    println(optionCat1 === Option(cat1))
    println(optionCat1 === optionCat2)

  }
}
