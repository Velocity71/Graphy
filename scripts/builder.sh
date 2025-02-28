#!/bin/bash

# This script builds the Graphy application
# It ensures the necessary directories exist (logs, build/classes, and build/dist)
# It compiles the java source code into .class bytecode
# It packages the bytecode and dependencies into a JAR file

# Create the necessary directories.
# -p ensures parent directories are created if necessary
mkdir -p logs
mkdir -p build/classes
mkdir -p build/dist

CLASSPATH=$(find libs -name "*.jar" -print0 | xargs -0):.
# Compile Java source code
# -d specifies the destination directory: build/classes/
# -cp specifies the classpath for dependencies: libs/
# *.java compiles all the java files in that directory
javac -d build/classes -p libs src/main/java/dev/velocity71/Graphy/*.java

# Build the JAR file
# c creates a new JAR file
# v provides a verbose output
# f specifies the name of the JAR file: build/dist/Graphy.jar
# e specifies the entry point (i.e. class containing the main method): dev/velocity71/Graphy/Main
    # -C gives the location of the files to be included in the JAR file: all compiled .class files from the project (build/classes/) and the config files (src/main/resources/)
jar cvfe build/dist/Graphy.jar dev.velocity71.Graphy.Main -C build/classes . -C src/main/resources . -C libs/ .