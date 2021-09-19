package ce101

import fs2._
import fs2.io.file._
import cats.effect._

import java.nio.file.Paths

object Fs2RenameOP extends IOApp.Simple:
  val dir = RenameOP.dir
  override def run =
    Files[IO]
      .walk(Path(dir))
      .evalFilter(file => Files[IO].isRegularFile(file))
      .evalMap { file =>
        val newName = Paths.get(RenameOP.newName(file.toString, dir))
        IO { println(file) } 
        // *> Files[IO].move(file.toNioPath, newName)
      }
      .compile
      .drain
