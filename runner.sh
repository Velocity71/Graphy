#!/bin/bash

# delete the target directory and it's contents. Then compile and run tests and package into a .jar file.
#mvn checkstyle:check clean package

# run the code
java --module-path /Library/Java/Extensions/lib \
		 --add-modules javafx.base,javafx.controls,javafx.graphics,javafx.fxml \
		 -cp target/classes:target/Graphy-1.0-SNAPSHOT.jar dev.velocity71.Graphy.Main

# try to create an application with a built-in JVM in a .app form.
#jlink --module-path /Library/Java/Extensions/lib \
#  	   --add-modules javafx.base,javafx.controls,javafx.graphics \
#      --output custom-runtime
#jpackage --name Graphy \
#         --input target \
#         --main-jar Graphy-1.0-SNAPSHOT.jar \
#         --runtime-image custom-runtime \
#         --type app-image
