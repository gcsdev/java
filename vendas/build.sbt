//name := "Vendas"

//version := "1.0-SNAPSHOT"

//lazy val root = (project in file(".")).enablePlugins(PlayJava)

//scalaVersion := "2.12.8"

//javacOptions ++= Seq(
//  "-encoding", "UTF-8",
//  "-parameters",
//  "-Xlint:unchecked",
//  "-Xlint:deprecation",
//  "-Werror"
//)

//crossScalaVersions := Seq("2.11.12", "2.12.7")

//libraryDependencies += guice

// Test Database
//libraryDependencies += "com.h2database" % "h2" % "1.4.197"

// Testing libraries for dealing with CompletionStage...
//libraryDependencies += "org.assertj" % "assertj-core" % "3.11.1" % Test
//libraryDependencies += "org.awaitility" % "awaitility" % "3.1.3" % Test

// Make verbose tests
//testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))



name := "vendas"
organization := "equals"
version := "1.0-SNAPSHOT"
lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)
scalaVersion := "2.12.4"

enablePlugins(PlayJava)

libraryDependencies ++= Seq(
	guice,
	javaForms,
	javaCore,
	javaJdbc,
	cache,
	"javax.persistence" % "javax.persistence-api" % "2.2",	
	 "javax.persistence" % "persistence-api" % "1.0.2",
	"org.eclipse.persistence" % "org.eclipse.persistence.core" % "2.7.1",
	"org.eclipse.persistence" % "org.eclipse.persistence.jpa" % "2.7.1",	
    "mysql" % "mysql-connector-java" % "8.0.11",
    "org.json" % "json" % "20180130"
    
)


