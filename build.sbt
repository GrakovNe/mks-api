organization in ThisBuild := "org.grakovne"
version in ThisBuild := "1.0"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.13.0"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lagomServiceLocatorAddress in ThisBuild := "0.0.0.0"

lazy val `mks-api` = (project in file("."))
  .aggregate(`mks-api-api`, `mks-api-impl`)

lazy val `mks-api-api` = (project in file("mks-api-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      lagomScaladslPubSub
    )
  )

lazy val `mks-api-impl` = (project in file("mks-api-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`mks-api-api`)