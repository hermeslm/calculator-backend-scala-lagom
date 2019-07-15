# Calculator using Lagom service in Scala

Lagom has great [documentation](https://www.lagomframework.com/documentation/)
and [examples](https://www.lagomframework.com/documentation/1.3.x/scala/LagomExamples.html) but even the
[helloworld](https://www.lagomframework.com/documentation/1.3.x/scala/IntroGetStarted.html) is using persistent
entities, multiple services and dependency injection. In a word, it could be simpler. This small project intends to be
such minimal example.

## Usage

Run service locally:
`sbt runAll`

Run tests:
`sbt test`

Build a docker image and publish it locally:
`sbt docker:publishLocal`

Run docker image:
`docker run -p 9000:9000 hello-lagom:1.0-SNAPSHOT`

Verify by opening http://localhost:9000/api/compute/{expresion} in your browser!# scala-calculator
