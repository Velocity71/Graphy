package dev.velocity71.Graphy;

import java.io.InputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Properties;

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
     * Logger for the Graphy application.
    */
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * File handler for writing logs to a file
    */
    private static FileHandler logHandler;

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
    public static void main(String[] args) throws IOException {
        loadConfig(); // Load the config file first.

        instantiateLogHandler(); // instantiates the logger's handler
        logger.info("Starting Graphy");

        // Create a new output frame.
        new OutputFrame();

        // Close the log handler to flush and release resources.
        logHandler.close();

        logger.info("Exiting Graphy"); // Indicate a clean exit.
    }

    /**
     * Loads the configuration settings from the "config.properties" resource.
     *
     * @throws IOException If the config.properties file cannot be found or read.
    */
    private static void loadConfig() throws IOException {
        try {
            // Locate the config file.
            InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                // More specific error message for debugging.
                throw new IOException("Unable to find config.properties resource.");
            }
            config.load(input);
        } catch (IOException e) {
            // Wrap original exception for better context in stack trace.
            throw new IOException("Error loading config.properties", e);
        }
    }

    /**
     * Initialize the log-specific file handler, it's file path, formatter, and level.
     *
     * @throw's IOException if there is an error creating or configuring the handler.
    */
	private static void instantiateLogHandler() throws IOException {
	   try {
			String logFilePath = config.getProperty("log.file");
			if (logFilePath == null || logFilePath.trim().isEmpty()) {
			    throw new IOException("log.file property not found or is empty in config.properties");
			}

			logHandler = new FileHandler(logFilePath, true); // Append to the log file.
			logHandler.setFormatter(new SimpleFormatter()); // Use a simple text format.
			logger.addHandler(logHandler);
			logger.setLevel(Level.INFO); // Minimum logging level is INFO
		} catch (final IOException e) {
		    // provide a more specific error message for debuging.
			throw new IOException("Failed to instantiate log file handler", e);
		}
	}

	/**
	 * Getter method for the Logger. Allows all other classes to log using this logger
	 *
	 * @return Logger
	*/
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Getter method for the configuration property by key.
	 *
	 * @param key The key of the property to retrieve.
	 * @return The value of the property associated with the key, or null if the key is not found.
	*/
	public static String getConfigProperty(String key) {
	   return config.getProperty(key);
	}
}