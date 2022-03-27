#! /bin/bash

echo "Detecting JUnit..."

filename=junit.jar
lib_dir=../libs


if [ $(find $lib_dir -name $filename) ]; then 
  echo "$filename was found in $lib_dir"
else
  mkdir ../libs
  echo "$filename not found."
  echo "$filename will be downloaded."
  wget -O ../libs/junit.jar 'https://search.maven.org/remotecontent?filepath=org/junit/platform/junit-platform-console-standalone/1.8.2/junit-platform-console-standalone-1.8.2.jar'
fi

echo "Compiling Unit Tests..."

javac -cp ../libs/junit.jar ousbtests.java

echo "Running Unit Tests..."

java -cp ../libs/junit.jar:. org.junit.runner.JUnitCore ousbtests


