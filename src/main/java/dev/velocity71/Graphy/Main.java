package dev.velocity71.Graphy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Entry point for the application.
// Bootstraps application, sets up the stage, and loads the 'control' scene.
public class Main extends Application {

	// Variable to store the config data loaded from 'src/main/resources/config.properties'. Holds filepaths to '.log' and '.fxml' files used in the program.
	private static Properties config = new Properties();

	// Main method for application.
    public static void main(String[] args) throws IOException{
        // Config file must be loaded before anything else is done.
        loadConfig();

        // Entry point for JavaFX
        launch(args);
    }


    // Override start() method. start() is the main method of a JavaFX applicaton.
    @Override public void start(Stage controlStage) throws IOException {

        // Creates the root node of the scene from 'src/main/resources/fxml/control.fxml', which controls the rest of the applicaton.
        Parent control = FXMLLoader.load(getClass().getClassLoader().getResource(config.getProperty("control.fxml.path")));

        controlStage.setScene(new Scene(control)); // Creates a scene from the previously loaded 'control' node and sets it as the content of the stage (window)
        controlStage.setTitle("Graphy");
        controlStage.show();
    }

    // Helper method that loads the data from the 'config.properties' file into the 'config' Properties object.
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
