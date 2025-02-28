#!/bin/bash

# This script runs the Graphy application, and assumes that the builder.sh script has previously been run.
# It removes the previous log file if it exists
# It runs the compiled bytecode

# Delete log if it exists
rm logs/Graphy.log

# Run the application by running compiled bytecode.
# -cp specifies the classpath for the code and it's dependencies (libs and src/main/resources)
# dev.velocity71.Graphy.Main is the class that contains the main method
java -cp build/classes:src/main/resources:libs dev.velocity71.Graphy.Main