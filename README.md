# Elements examples with Groovy

These are the Vaadin Elements demos implemented with
[Groovy](http://groovy-lang.org/).  While making the original examples work
was fun, it just was a transcription of the Java/Scala code.

So I started to make better use of Groovy here by defining a DSL to build UIs
with a syntax similar to other markup builders.  See the `ElementsExampleUI`
for how to use it and `GroovyElements` for the implementation.

The project is built with SpringBoot, vaadin4spring, and gradle. After cloning
the project you can run

    ./gradlew bootRun

to start it. After a while the app should be available at
[localhost](http://localhost:8080).

IntelliJ project files can be created with

    ./gradlew idea

after which you can import the project to IntelliJ.
