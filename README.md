# ProductParser [![Build Status](https://travis-ci.org/jrry/ProductParser.svg?branch=master)](https://travis-ci.org/jrry/ProductParser)
Parse products from websites to a formatted file

## Build
```
$ mvn clean package
```

## Example usage

```java
java -jar productparser-1.1-SNAPSHOT.jar "https://www.ceneo.pl/Filmy_Blu-ray/Gatunek:Komedie.htm" output.xml
```
```java
java -jar productparser-1.1-SNAPSHOT.jar "https://www.ceneo.pl/Filmy_Blu-ray/Gatunek:Komedie.htm" output.json -q
```
### Parameters
**-q** enable multi threads base64 image generator

