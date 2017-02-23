Vert.x 3 simple Gradle project example
======================================

This project shows a very simple example project using Gradle, which has an HTTP server with a very simple API and backend.

In this example Vert.x is used embedded. I.e. we use the Vert.x APIs directly in our own classes rather than deploying
the code in verticles.

To run the service, Java 8 needs to be available on your machine.

You can run or debug the example in your IDE by just right clicking the main class (`BookService`) and *Run as...* or *Debug as...*

To use the project in Eclipse, first run

```
./gradlew eclipse
```

to generate an Eclipse project that you can import into your workspace.

The build.gradle uses the Gradle shadowJar plugin to assemble the application and all it's dependencies into a single "fat" jar.

To build the "fat jar"

```
./gradlew shadowJar
```

Because the application plugin is being used, you may directly run the application:

```
./gradlew run
```

Now point your browser at <http://localhost:8080>
