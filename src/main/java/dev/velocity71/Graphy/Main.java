package dev.velocity71.Graphy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the Graphy application. Launches the JavaFX application and
 * handles application-wide initialization, including loading Configuration
 * and setting up logging.
 * Contains the entry point for both Java(main) and JavaFX(start)
 */
public class Main extends Application {

    /**
     * Properties object to store configuration parameters loaded from
     * config.properties.
     */
    private static Properties config = new Properties();

    /**
     * The logger for the Main class.
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Constant storing the Class Loader for this class (Main).
     */
    private static final ClassLoader MAINLOADER = Main.class.getClassLoader();

    /**
     * The main method, serving as the entry point for the application. Loads
     * the configuration, initializes logging, and launches the JavaFX
     * application. Includes robust error handling to catch and report
     * exceptions during startup.
     *
     * @param args Command line arguments (unused).
     * @throws IOException If there is an error loading the config file.
     */
    public static final void main(final String[] args) throws IOException {

        try {
            loadConfig();

            try {
                new ClassLogger(Main.class.getSimpleName());
                LOGGER.info("Instantiated Main class logger.");
            } catch (IOException e) {
                System.err.println(
                    "Error initializing Main class logger: " + e.getMessage()
                );
            }
            launch(args);

        /* Improved error handling: prints cause and stack trace for all
            exceptions in the chain. */
        } catch (IOException e) {
           System.err.println(
               "A fatal error occurred during application startup"
           );
           for (; e != null; e = e.getCause()) {
               System.err.println(e);
               for (StackTraceElement e: t.getStackTrace()) {
                   System.err.println("\tat " + e);
               }
           }
        }

            LOGGER.info(
                "Successfully loaded configuration file " +
                "and initialized Main class logger."
            );

        try {
        launch(args);
        } catch (Throwable t) {
            /* Improved error handling: prints cause and stack trace for all
               exceptions in the chain. */
            for (; t != null; t = t.getCause()) {
                System.err.println(t);
                for (StackTraceElement e: t.getStackTrace()) {
                    System.err.println("\tat " + e);
                }
            }
        }
    }


    /**
     * JavaFX start method. Loads the FXML file and sets up the application
     * stage.
     *
     * @param appStage The primary stage for the application.
     * @throws IOException If there is an error loading the FXML file.
     */
    @Override public final void start(final Stage appStage) throws IOException {

        final String fxmlPath = config.getProperty("app.fxml.path");
        final Parent app = FXMLLoader.load(MAINLOADER.getResource(fxmlPath));

        appStage.setScene(new Scene(app));
        appStage.setTitle("Graphy");
        appStage.show();
    }

    /**
     * Loads configuration parameters from the config.properties file.
     *
     * @throws IOException If the config file is not found or cannot be read.
     */
    public static final void loadConfig() throws IOException {
        try {
            final InputStream input =
                MAINLOADER.getResourceAsStream("config.properties");
            if (input == null) {
                throw new
                    IOException("Unable to find config.properties resource.");
            }
            config.load(input);
        } catch (final IOException e) {
            throw new IOException("Error loading config.properties", e);
        }
    }

    /**
     * Get method for the config Properties object. Used in testing.
     *
     * @return config A Properties object that store configuration parameters
     *  loaded from config.properties.
     */
    public static final Properties getConfig() {
        return config;
    }
}
