package scalawithcats.chapterthree


trait Printable [A] { self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String = {
        self.format(func(value))
      }
    }

}


object Printable extends App {

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        s"'${value}'"
    }
  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    def format(value: Boolean): String =
      if(value) "yes" else "no"
  }

  implicit val intPrintable: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

//  implicit def boxPrintable[A] (implicit printer: Printable[A]): Printable[Box[A]] = {
//    new Printable[Box[A]] {
//      override def format(box: Box[A]): String = printer.format(box.value)
//    }
//  }

  implicit def boxPrintable[A] (implicit printer: Printable[A]): Printable[Box[A]] = printer.contramap[Box[A]](_.value)

  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))

  println(format("hello"))
  // res2: String = "'hello'"
  println(format(true))
  // res3: String = "yes"

  println(format(Box("Hello World")))
  println(format(Box(false)))
  println(format(Box(123)))
}
