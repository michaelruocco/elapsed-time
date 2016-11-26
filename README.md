# Elapsed Time

This project gives the ability to be able to easily create and work with an elapsed time
object. It can be created by passing a long value of milliseconds or a formatted string.
The object has no notion of time zone or date, it is focused only on elapsed time values.
This project is my attempt at the connect four kata exercise that is outlined here:

## Usage

To use the library from a program you will need to add a dependency to your project. In
gradle you would do this by adding the following to your build.gradle file:

```
dependencies {
    classpath 'com.github.michaelruocco:elapsed-time:1.0.0'
}
```

You can then create an elapsed time value directly by passing a milliseconds
value like so:

```
ElapsedTime time = new ElapsedTime(1000); // creates an instance with a value of 1 second
```

To build a value from a string to you can use the ElapsedTimeConverter:

```
ElapsedTimeConverter converter = new ElapsedTimeConverter();
ElapsedTime time = converter.toElapsedTime("00:00:01.000"); // creates an instance with a value of 1 second
```

The converter can also be used to convert an elapsed time instance to a formatted string:

```
ElapsedTime time = new ElapsedTime(1000);
String formattedTime = converter.toString(time); // will give a string value of "00:00:01.000"
```

Additionally some component classes are available for formatting, parsing and validation if
required.

## Running the Tests

You can run the unit tests for this project by running:

```
gradlew clean build
```

## Checking dependencies

You can check the current dependencies used by the project to see whether
or not they are currently up to date by running the following command:

```
gradlew dependencyUpdates
```