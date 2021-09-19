val scala3Version = "3.0.1"
val catsEffectVersion = "3.2.9"
val catsCoreVersion = "2.6.1"
val fs2Version = "3.1.2"
val dottyCPSasyncVersion = "0.9.3"
val cpsAsyncConnectCatsEffectVersion = "0.9.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-cats",
    version := "0.1.1",
    scalaVersion := scala3Version,
    Compile / run / mainClass := Some("ce101.SimpleHello"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % catsCoreVersion,
      "org.typelevel" %% "cats-effect" % catsEffectVersion,
      "org.typelevel" %% "cats-effect-kernel" % catsEffectVersion,
      "org.typelevel" %% "cats-effect-std" % catsEffectVersion,
      "co.fs2" %% "fs2-io" % fs2Version,
      "com.github.rssh" %% "dotty-cps-async" % dottyCPSasyncVersion,
      "com.github.rssh" %% "cps-async-connect-cats-effect" % cpsAsyncConnectCatsEffectVersion,
      "org.scalameta" %% "munit" % "0.7.26" % Test
    ),
    scalacOptions ++= Seq(
      "-rewrite",
      "-indent",
      "-feature",
      "-deprecation",
      "-unchecked"
      // "-Xprint:typer"
    )
  )

// mainClass in Compile := Some("org.project.my.Main")
