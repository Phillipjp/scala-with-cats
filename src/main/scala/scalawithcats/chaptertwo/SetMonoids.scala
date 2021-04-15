package scalawithcats.chaptertwo

object SetMonoids {

  implicit def setUnionMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      override def empty: Set[A] = Set.empty

      override def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
    }

//  implicit def setIntersectionMonoid[A]: Monoid[Set[A]] =
//    new Monoid[Set[A]] {
//      override def empty: Set[A] = Set.empty
//
//      override def combine(x: Set[A], y: Set[A]): Set[A] = x.intersect(y)
//    }

  // why can't there be an empty intersection?
  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] =
    new Semigroup[Set[A]] {
      override def combine(x: Set[A], y: Set[A]): Set[A] = x.intersect(y)
    }

  implicit def setSymmetricDifferenceMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      override def combine(x: Set[A], y: Set[A]): Set[A] =  x.diff(y).union(y.diff(x))

      override def empty: Set[A] = Set.empty
    }

}
