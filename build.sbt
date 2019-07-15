organization in ThisBuild := "com.lightbend"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `hello` = (project in file("."))
  .aggregate(`hello-api`, `hello-impl`)

lazy val `hello-api` = (project in file("hello-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-impl` = (project in file("hello-impl"))
  .enablePlugins(LagomScala)
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      scalaTest,
      filters
    ),
    packageName in Docker := "hello-lagom",
    dockerExposedPorts in Docker := Seq(9000)
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`hello-api`)

