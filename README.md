[![Build Status](https://travis-ci.org/fridayy/nyx-fhj.svg?branch=master)](https://travis-ci.org/fridayy/nyx-fhj)
#NYX
_FH Joanneum Project SWD14_

Project Work for the Course: Project Work 1 (Egon Teiniker)

Running NYX:
1. Import Gradle Project "build.gradle" within the main directory into IntelliJ Idea or Eclipse.
   Or run `gradle assemble` on the command line and navigate into the corresponding build folders and run the .jar files
   by using `java -jar [file]` on the command line.

2. Start the Config Service: ninja.harmless.config.Bootstrap
3. Start the Eureka Service: ninja.harmless.nyx.eureka.Bootstrap
4. Start the Zuul API Gateway: ninja.harmless.nyx.apigateway.Bootstrap
5. Start nyx-data-acquistion: ninja.harmless.nyx.Bootstrap
6. Start nyx-statistics: ninja.harmless.nyx.stats.Bootstrap
7. Start nyx-trailer-service: ninja.harmless.nyx.trailer.Bootstrap

Access the API by entering `http://localhost:8080/v1/movie/{title}` in your Browser.
Enter any movie title you want to receive information about that movie.


_Benjamin K._
