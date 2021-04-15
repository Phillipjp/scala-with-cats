package scalawithcats.chaptertwo

import cats.Monoid
import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)

object SuperAdder {

  //  def add(items: List[Int]): Int = items.sum
  def add(items: List[Int]): Int = items.foldLeft(0)(_+_)

  def add[A](items: List[A])(implicit monoid: cats.Monoid[A]): A = items.foldLeft(monoid.empty)(_ |+| _)

  implicit val orderMonoid: cats.Monoid[Order] =
    new cats.Monoid[Order] {
      override def empty: Order = Order(0,0)

      override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    }


}
