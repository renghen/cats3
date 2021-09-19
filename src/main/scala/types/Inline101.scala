package types

import scala.compiletime.*
import scala.compiletime.ops.int.*

trait Positive[T <: Int]
object Positive:
  inline given [T <: Int]: Positive[T] =
    inline val t = compiletime.constValue[T]
    inline if (t > 0) then new Positive[T] {}
    else compiletime.error("Expected positive argument")

  val x = summon[Positive[4]]
