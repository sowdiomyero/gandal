import sbt.Keys._
import com.github.play2war.plugin._

name := """E-Politic"""

version := "1.0-SNAPSHOT"

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  javaEbean,
  cache,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.typesafe" %% "play-plugins-mailer" % "2.2.0",
  "mysql" % "mysql-connector-java" % "5.1.18",
  filters
)

libraryDependencies ++= Seq(
  javaWs
)

resolvers ++= Seq(
    "Apache" at "http://repo1.maven.org/maven2/",
    "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
    "Sonatype OSS Snasphots" at "http://oss.sonatype.org/content/repositories/snapshots"
)


lazy val root = (project in file(".")).enablePlugins(play.PlayJava)
