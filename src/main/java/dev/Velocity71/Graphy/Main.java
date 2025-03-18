package dev.velocity71.Graphy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the Graphy application. Contains the control scene.
 */
public class Main extends Application {

	// Variable to contain the information in the config file, see src/main/resources/config.properties.
	private static Properties config = new Properties();

    @Override
    public void start(Stage controlStage) throws Exception {

    	// Must load the config file before anything else.
    	loadConfig();

     	// Load data for the control scene from the control.FXML file located at src/main/resources/fxml/control.fxm.
        Parent control = FXMLLoader.load(getClass().getClassLoader().getResource(config.getProperty("control.fxml.path")));


        controlStage.setScene(new Scene(control));
        controlStage.setTitle("Graphy");
        controlStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void loadConfig() throws IOException {
            try {
                final InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties"); // Locate config file.
                if (input == null) {
                    throw new IOException("Unable to find config.properties resource."); // More specific error message for debugging.
                }
                config.load(input);
            } catch (final IOException e) {
                throw new IOException("Error loading config.properties", e); // Wrap original exception for better context in stack trace.
            }
        }
}
