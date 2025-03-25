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
// Bootstraps application, sets up the stage, and loads the 'app' scene.
public class Main extends Application {

	// Variable to store the config data loaded from 'src/main/resources/config.properties'. Holds filepaths to '.log' and '.fxml' files used in the program.
	private static Properties config = new Properties();

	// Main method for application.
    public static void main(String[] args) throws IOException{
        // Config file must be loaded before anything else is done.
        loadConfig();

        // Entry point for JavaFX. Wrapped in try-catch statement that prints full stack trace.
        try {
        launch(args);
        } catch (Throwable t) {
       		for(; t != null; t = t.getCause()) {
                System.err.println(t);
                for(StackTraceElement e: t.getStackTrace())
                    System.err.println("\tat "+e);
            }
        }
    }


    // Override start() method. start() is the main method of a JavaFX applicaton.
    @Override public void start(Stage appStage) throws IOException {

        // Creates the root node of the scene from 'src/main/resources/fxml/app.fxml', which controls the rest of the applicaton.
        Parent app = FXMLLoader.load(getClass().getClassLoader().getResource(config.getProperty("app.fxml.path")));

        appStage.setScene(new Scene(app)); // Creates a scene from the previously loaded 'app' node and sets it as the content of the stage (window)
        appStage.setTitle("Graphy");
        appStage.show();
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
