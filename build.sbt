organization in ThisBuild := "com.lightbend"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `calculator` = (project in file("."))
  .aggregate(`calculator-api`, `calculator-impl`)

lazy val `calculator-api` = (project in file("calculator-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `calculator-impl` = (project in file("calculator-impl"))
  .enablePlugins(LagomScala)
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      scalaTest,
      filters
    ),
    packageName in Docker := "calculator-lagom",
    dockerExposedPorts in Docker := Seq(9000)
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`calculator-api`)

