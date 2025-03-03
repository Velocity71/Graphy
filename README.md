# Graphy
A 2-dimensional function grapher coded in Java.

This program is not yet functional.

# Just resetted using apache maven

run the build by entering the root directory and running the following command:

java --module-path /Library/Java/Extensions/lib --add-modules javafx.base,javafx.controls,javafx.graphics -cp target/Graphy-1.0-SNAPSHOT.jar dev.velocity71.Graphy.Main

because the app is not yet functional for computers that do not java the JDK (or JRE?) and the JavaFX SDK installed, please first install the openJDK from Java's website or through brew, as well as the JavaFX SKD through gluon.
I use macOS and have put the "lib" folder from the JavaFX version 25-ea+5 into the /Library/Java/Extensions folder. Only when these things are done will you be able to run the program.
