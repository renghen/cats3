package types

import scala.compiletime.*
import scala.compiletime.ops.int.*

object MatchTypes:

  type Positive2[N <: Int] <: Int = N match
    case S[m] => m | Positive2[m]
    case 0    => 0

  val positive2: Positive2[100] = 1

  type Positive3[N <: Int] <: Int = (N >= 0) match
    case true => Positive2[N]
    case _    => 0

  val positive3: Positive3[100] = 43

  type Positive4[N <: Int, Acc <: Int] <: Int = (N >= 0) match
    case true => Positive4[N - 1, Acc | N]
    case _    => Acc

  val positive4: Positive4[10, 0] = 8

// type Bound[Xs <: Int, Ys <: Int] <: Int = Ys match
//   case Xs =>
//     Xs match
//       case ops.int.>[Xs, Ys] => Bound[Ys, Xs]
//       case _                 => Xs
//   case ops.int.>=[Ys, 1] => 3 | 1 | 2
//   case Int               => Ys | Bound[Xs, ops.int.-[Ys, 1]]

// val i: Bound[0, 1] = 1
