package scalawithcats.chaptertwo

object BooleanMonoids {

  implicit val booleanAndMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty: Boolean = true

      override def combine(x: Boolean, y: Boolean): Boolean = x && y
    }

  implicit val booleanOrMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty: Boolean = false

      override def combine(x: Boolean, y: Boolean): Boolean = x || y
    }

  implicit val booleanEitherMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty: Boolean = false

      override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
    }

  implicit val booleanXnorMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      override def empty: Boolean = true

      override def combine(x: Boolean, y: Boolean): Boolean = (x || !y) && (!x || y)
    }
}
