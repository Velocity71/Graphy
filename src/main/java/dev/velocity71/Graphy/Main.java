package dev.velocity71.Graphy;

import dev.velocity71.Graphy.Parsing.FunctionParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
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
 *
 * @since 0.1.2
 * @author Velocity71
 * @version 0.2.15
 */
public class Main extends Application {

    /**
     * Properties object to store configuration parameters loaded from
     * config.properties.
     *
     * @since 0.1.2
     */
    private static Properties config = new Properties();

    /**
     * Constant storing the Class Loader for this class (Main).
     *
     * @since 0.2.10
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
     * @since 0.1.2
     * @author Velocity71
     * @version 0.23
     */
    public static final void main(final String[] args) {
        // Wrapping exceptions for better context in stack trace.
        try {
            loadConfig();
            launch(args);

            String function = "2*ln(x)";
            double[] input = {
                -10,
                -9,
                -8,
                -6,
                -5,
                -4,
                -3,
                -2,
                -1,
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
            };
            HashMap<Double, String> result = FunctionParser.getFunctionTable(
                function,
                "x",
                input
            );

            for (double i : input) {
                System.out.println("f(" + i + ") = " + result.get(i));
            }
        } catch (final Throwable t) {
            System.err.println(
                "A fatal error occured during application startup"
            );
            System.err.println(getFullStackTrace(t));
        }
    }

    /**
     * JavaFX start method. Loads the FXML file and sets up the application
     * stage.
     *
     * @param appStage The primary stage for the application.
     * @throws IOException If there is an error loading the FXML file.
     * @since 0.2.2
     * @Author Velocity71
     * @version 0.11
     */
    @Override
    public final void start(final Stage appStage) throws IOException {
        final String fxmlPath = config.getProperty("app.fxml.path");
        final Parent app = FXMLLoader.load(MAINLOADER.getResource(fxmlPath));

        appStage.setScene(new Scene(app));
        appStage.setTitle("Graphy");
        appStage.show();
    }

    /**
     * Loads configuration parameters from the config.properties file.
     *
     * @throws IOException If the configuration file was not found.
     * @throws IOException If an Error ocurred when reading the file.
     * @since 0.1.4
     * @author Velocity71
     * @version 0.11
     */
    private static void loadConfig() throws IOException {
        try {
            final InputStream file = MAINLOADER.getResourceAsStream(
                "config.properties"
            );
            if (file == null) { // Throw IOException if file is not found.
                throw new IOException("Unable to find config.properties file.");
            } else {
                config.load(file);
            }
        } catch (final IOException e) {
            // Re-throw exception for more specific context in stack trace.
            throw new IOException("Unable to load config.properties file.", e);
        }
    }

    /**
     * Function to ensure the full stack trace is printed to the terminal.
     *
     * @param t The throwable to get the full stack trace of.
     * @return The full stack trace.
     * @since 0.2.11
     * @author Velocity71
     * @version 0.3
     */
    private static String getFullStackTrace(final Throwable t) {
        String stackTrace = "";
        for (Throwable tc = t; tc != null; tc = tc.getCause()) {
            System.err.println(tc);
            for (final StackTraceElement e : tc.getStackTrace()) {
                stackTrace += "\n\tat " + e;
            }
        }

        return stackTrace;
    }

    /**
     * Get method for the config Properties object. Used in testing.
     *
     * @return config A Properties object that store configuration parameters
     *  loaded from config.properties.
     * @since 0.2.10
     * @author Velocity71
     * @version 0.3
     */
    protected static final Properties getConfig() {
        return config;
    }
}
