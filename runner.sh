#!/bin/bash

# delete the target directory and it's contents. Then compile and run tests and package into a .jar file.
mvn clean package

rm -r logs
mkdir -p logs

# run the code
java \
     -Djava.util.logging.config.file=target/classes/logging.properties \
     --module-path /Library/Java/Extensions/lib \
	 --add-modules javafx.base,javafx.controls,javafx.graphics,javafx.fxml \
	 --class-path target/classes:target/Graphy-1.0-SNAPSHOT.jar dev.velocity71.Graphy.Main

# try to create an application with a built-in JVM in a .app form.
#jlink --module-path /Library/Java/Extensions/lib \
#  	   --add-modules javafx.base,javafx.controls,javafx.graphics \
#      --output custom-runtime
#jpackage --name Graphy \
#         --input target \
#         --main-jar Graphy-1.0-SNAPSHOT.jar \
#         --runtime-image custom-runtime \
#         --type app-image
