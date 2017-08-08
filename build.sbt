name := "metromonitor"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "org.twitter4j" % "twitter4j-core" % "3.0.5",
  "org.twitter4j" % "twitter4j-stream" % "3.0.5",
  "org.scalaj" % "scalaj-http_2.11" % "2.3.0",
  "org.json4s" %% "json4s-native" % "3.3.0",
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)
