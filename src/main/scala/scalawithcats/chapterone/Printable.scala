package scalawithcats.chapterone

trait Printable [A] {

  def format(value: A): String

}

object PrintableInstances{

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      override def format(value: String): String = value
    }

  implicit val intPrintable: Printable[Int] =
    new Printable[Int] {
      override def format(value: Int): String = value.toString
    }

  implicit val catPrintable: Printable[Cat] =
    new Printable[Cat] {
      override def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
    }

}

object Printable {

  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))

}

object PrintableSyntax {

  implicit class PrintableOps[A](value: A){
    def format(implicit printable: Printable[A]): String = printable.format(value)

    def print(implicit printable: Printable[A]): Unit = println(format)
  }
}
