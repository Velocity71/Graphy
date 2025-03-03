#!/bin/bash

java --module-path /Library/Java/Extensions/lib \
		 --add-modules javafx.base,javafx.controls,javafx.graphics \
		 -cp target/Graphy-1.0-SNAPSHOT.jar dev.velocity71.Graphy.Main

#jlink --module-path /Library/Java/Extensions/lib \
#  	   --add-modules javafx.base,javafx.controls,javafx.graphics \
#      --output custom-runtime

#jpackage --name Graphy \
#         --input target \
#         --main-jar Graphy-1.0-SNAPSHOT.jar \
#         --runtime-image custom-runtime \
#         --type app-image
