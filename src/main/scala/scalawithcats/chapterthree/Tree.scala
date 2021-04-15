package scalawithcats.chapterthree

import cats._
import cats.implicits.toFunctorOps

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object ChapterThree{

  implicit val treeFunctor: Functor[Tree] =
    new Functor[Tree] {
      override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = {
        fa match {
          case Branch(left, right) => Branch(map(left)(f), map(right)(f))
          case Leaf(value) => Leaf(f(value))
        }
      }
    }

  def main(args: Array[String]): Unit = {


    val res1 = Tree.leaf(100).map(_ * 2)

    println(res1)

    println(Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2))
  }
}

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)
}