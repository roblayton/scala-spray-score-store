name := "scala-spray-rest"

version := "1.0"

scalaVersion := "2.10.4"

val sprayVersion = "1.3.1"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-can" % sprayVersion,
  "io.spray" %% "spray-routing" % sprayVersion,
  "io.spray" %% "spray-client" % sprayVersion,
  "io.spray" %% "spray-testkit" % sprayVersion % "test",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "com.typesafe.akka" %% "akka-actor" % "2.3.6",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.6",
  "com.typesafe.akka" %% "akka-http-experimental" % "0.7",
  "com.typesafe.slick" %% "slick" % "1.0.1",
  "org.json4s" %% "json4s-native" % "3.2.10",
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test"
)

resolvers += "spray repo" at "http://repo.spray.io"

assemblySettings
