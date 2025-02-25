package dev.velocity71.Graphy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main class for the Graphy application.
 *  - Loads the configuration.
 *  - Initializes the logger.
 *  - Creates the output frame.
 *
 * @author Velocity71
 * @version 0.1
 * @since 0.1
*/

public class Main {

    /**
     * App logger for the Graphy application.
    */
    private static final Logger appLogger = Logger.getLogger(Main.class.getName());

    /**
     * Error logger for the Graphy application.
    */
    private static final Logger errLogger = Logger.getLogger(Main.class.getName());

    /**
     * File handler for writing app logs to a file
    */
    private static FileHandler appLogHandler;

    /**
     * File handler for writing error logs to a file
    */
    private static FileHandler errLogHandler;

    /**
     * Configuration properties loaded from the config.properties file.
    */
    private static Properties config = new Properties();

    /**
     * Main Method for the Graphy application
     *
     * @param args Command line arguments (unused).
     * @throws IOException If there is an issue with the log handler or config file.
    */
    public static void main(final String[] args) throws IOException {
        loadConfig(); // Load the config file first.

        instantiateLogHandlers(); // instantiates the logger's handler
        appLogger.info("Starting Graphy");

        // Create a new output frame.
        new OutputFrame();

        // Close the log handlers to flush and release resources.
        appLogHandler.close();
        errLogHandler.close();

        appLogger.info("Exiting Graphy"); // Indicate a clean exit.
    }

    /**
	 * Getter method for the configuration property by key.
	 *
	 * @param key The key of the property to retrieve.
	 * @return The value of the property associated with the key, or null if the key is not found.
	*/
	public static String getConfigProperty(final String key) {
	   return config.getProperty(key);
	}

    /**
	 * Getter method for the app Logger. Allows all other classes to log using this logger.
	 *
	 * @return Logger
	*/
	public static Logger getAppLogger() {
		return appLogger;
	}

	/**
	 * Getter method for the error Logger. Allows all other classes to log using this logger.
	 *
	 * @return Logger
	*/
	public static Logger getErrLogger() {
		return errLogger;
	}

	/**
     * Loads the configuration settings from the "config.properties" resource.
     *
     * @throws IOException If the config.properties file cannot be found or read.
    */
    private static void loadConfig() throws IOException {
        try {
            // Locate the config file.
            final InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                // More specific error message for debugging.
                throw new IOException("Unable to find config.properties resource.");
            }
            config.load(input);
        } catch (final IOException e) {
            // Wrap original exception for better context in stack trace.
            throw new IOException("Error loading config.properties", e);
        }
    }

	/**
     * Initialize the log-specific file handlers, their file paths, formatters, and levels.
     *
     * @throw's IOException if there is an error creating or configuring the handlers.
    */
	private static void instantiateLogHandlers() throws IOException {
	   try {
			final String appLogFilePath = config.getProperty("app.log.file");
			if (appLogFilePath == null || appLogFilePath.trim().isEmpty()) {
			    throw new IOException("app.log.file property not found or is empty in config.properties.");
			}

			final String errLogFilePath = config.getProperty("err.log.file");
			if (appLogFilePath == null || errLogFilePath.trim().isEmpty()) {
			    throw new IOException("err.log.file property not found or is empty in config.properties.");
			}

			appLogHandler = new FileHandler(appLogFilePath, true); // Append to the app log file.
			appLogHandler.setFormatter(new SimpleFormatter()); // Use a simple text format.
			appLogger.addHandler(appLogHandler);
			appLogger.setLevel(Level.INFO); // Minimum app logging level is INFO

			errLogHandler = new FileHandler(errLogFilePath, true); // Append to the error log file.
			errLogHandler.setFormatter(new SimpleFormatter()); // Use a simple text format.
			errLogger.addHandler(errLogHandler);
			errLogger.setLevel(Level.WARNING); // Minimum error logging level is WARNING

		} catch (final IOException e) {
		    // provide a more specific error message for debuging.
			throw new IOException("Failed to instantiate log file handlers", e);
		}
	}
}