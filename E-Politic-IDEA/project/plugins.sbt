// Comment to get more information during initialization
logLevel := Level.Debug

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Cloudbees
resolvers += "Sonatype OSS Snasphots" at "http://oss.sonatype.org/content/repositories/snapshots"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.0-RC2")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.0.0-RC2")

addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0-RC3")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.12.0")

addSbtPlugin("com.github.play2war" % "play2-war-plugin" % "1.3-beta3")
