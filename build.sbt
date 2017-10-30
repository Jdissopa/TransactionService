name := "restapi"

version := "1.0"

scalaVersion := "2.10.5"

val sprayVersion = "1.3.3"
val akkaVersion = "2.3.6"

libraryDependencies ++= Seq(
  "io.spray"              %% "spray-can"                % sprayVersion,
  "io.spray"              %% "spray-routing"            % sprayVersion,
  "io.spray"              %% "spray-json"               % sprayVersion,
  "com.typesafe.akka"     %% "akka-actor"               % akkaVersion
)
    