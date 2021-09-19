package rockthejvm.ce

import cats.effect.*
import scala.annotation.tailrec

object ExerciseIO101:

  def sequenceTakeLast[A, B](ioa: IO[A], iob: IO[B]): IO[B] =
    ioa.flatMap(_ => iob)
  // ioa *> iob //can also use

  def sequenceTakeFirst[A, B](ioa: IO[A], iob: IO[B]): IO[A] =
    ioa.flatMap(a => iob.map(_ => a))
  // ioa <* iob //can also

  def forever[A](io: IO[A]): IO[A] =
    io.flatMap(_ => forever(io))
    io.foreverM

  def convert[A, B](io: IO[A], value: B): IO[B] =
    io.map(_ => value)

  def asUnit[A](io: IO[A]): IO[Unit] =
    convert(io, ())

  def sumIO(n: Int): IO[Int] =
    if (n <= 0) then IO(0)
    else sumIO(n - 1).map(prev => n + prev)

  def fibonacci(n: Int): IO[BigInt] =
    if (n <= 2) then IO(BigInt(1))
    else
      for {
        a <- IO(fibonacci(n - 1)).flatten
        b <- IO(fibonacci(n - 2)).flatten
      } yield a + b

  @main
  def testFunc() =
    import cats.effect.unsafe.implicits.global
    fibonacci(50).flatMap { a => IO.println(a) }.unsafeRunSync()
