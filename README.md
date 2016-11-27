# Elapsed Time

[![Build Status](https://travis-ci.org/michaelruocco/elapsed-time.svg?branch=master)](https://travis-ci.org/michaelruocco/elapsed-time)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/elapsed-time/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/elapsed-time?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/elapsed-time/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.michaelruocco/elapsed-time)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/14bb0479ff524fd4a4ef2474077d8fd5)](https://www.codacy.com/app/michael-ruocco/elapsed-time?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/elapsed-time&amp;utm_campaign=Badge_Grade)

This project gives the ability to be able to easily create and work with an elapsed time
object. It can be created by passing a long value of milliseconds or a formatted string.
The object has no notion of time zone or date, it is focused only on elapsed time values.
This project is my attempt at the connect four kata exercise that is outlined here:

## Usage

To use the library from a program you will need to add a dependency to your project. In
gradle you would do this by adding the following to your build.gradle file:

```
dependencies {
    classpath 'com.github.michaelruocco:elapsed-time:1.0.2'
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