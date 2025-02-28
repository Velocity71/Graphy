# Graphy
A 2-dimensional function grapher coded in Java.

This program is not yet functional.

# Tour of the Code
src contains the source code for the file.
  src/main contains the main project code.
    src/main/java/dev/velocity71/Graphy contains all the Graphy source code.
    src/main/resources contains resources like the config.properties file used for logs.
  src/test mirrors src/main but is used for testing purposes.

build contains the compiled code and binaries.
  build/classes contains the raw .class files.
  build/libs contains the jar file for the project.

libs contains all the dependencies for the project, including JavaFX for GUI and JUnit for testing.

logs contains the log files for the project.

javadoc contains all the javadoc style documentation for the project.

scripts contains the scripting files for the project.
  scripts/builder.sh compiles the .class and .jar files, as well as creates the necessary directories.
  scripts/runner deletes the previous log file and runs the compiled .class files

.gitignore contains instructions for all the files github will ignore when code is committed.
LICENSE ontains the license information on the repository.
README.md (this file) contains information about the project and how to use it.

# How to Run
To run the program from source code, you can use the provided scripts. From the root directory (Graphy), run the following command:
  sh scripts/builder.sh
This compiles the source code into bytecode .class files, and gathers class files into a executable .jar file.
To run the program, there are two options:
  1. Run the bytecode directy. From the root directory, run the following command:
    sh scripts/runner.sh
  2. Run the jar file. From the root directory, run the following command:
    java -jar build/dist/Graphy.jar