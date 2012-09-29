import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {
	
  val appVersion = "1.0"

  val appDependencies = Seq(
    "com.github.seratch" %% "scalikejdbc" % "[1.3,)",
    "com.github.seratch" %% "scalikejdbc-play-plugin" % "[1.3,)",
    "org.slf4j" % "slf4j-simple" % "1.6.6"
  )

  lazy val root = PlayProject("ContextualSession", appVersion, appDependencies, mainLang = SCALA).settings(
  )

}
