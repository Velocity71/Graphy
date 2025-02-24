#!/bin/bash

# Create the necessary directories
mkdir -p logs
mkdir -p build/classes
mkdir -p build/libs

# Generate binaries to build/classes.
javac -d build/classes src/main/java/dev/velocity71/Graphy/*.java

# Build the JAR file to build/libs.
jar cf build/libs/Graphy.jar -C build/classes .