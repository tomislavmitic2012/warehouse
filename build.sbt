name := "warehouse"

version := "1.0"

lazy val `warehouse` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs, "com.google.guava" % "guava" % "18.0" )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  