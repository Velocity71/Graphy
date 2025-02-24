package dev.Velocity71.Graphy;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


import java.io.InputStream;
import java.util.Properties;

public class Main {

    // variables related to logging
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static FileHandler logHandler;

    private static Properties config = new Properties(); //added

    public static void main(String[] args) throws IOException {
        loadConfig(); //added Load configuration before instantiating the logger
        instantiateLogHandler();
        logger.info("Starting Graphy");

        new OutputFrame();

        logHandler.close();
    }

    private static void loadConfig() throws IOException { //added
        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            config.load(input);
        } catch (IOException e) {
            throw new IOException("Error loading config.properties", e);
        }
    }

    // instantiate the log file handler
	private static void instantiateLogHandler() throws IOException {
	   try {

			// added, get the log file path from the configuration
			String logFilePath = config.getProperty("log.file");
			if (logFilePath == null || logFilePath.trim().isEmpty()) {
			    throw new IOException("log.file property not found or is empty in config.properties");
			}


			logHandler = new FileHandler(logFilePath, true); // logs append to file "Graphy.log"
			logHandler.setFormatter(new SimpleFormatter()); // formatting type
			logger.addHandler(logHandler);
			logger.setLevel(Level.INFO); // logs saved must be INFO level or higher
		} catch (final IOException e) {
			throw new IOException("Failed to instantiate log file handler", e);
		}
	}

    public static Logger getLogger() {
		return logger;
	}

	// added, good practice to have a method to get config properties
	public static String getConfigProperty(String key) {
	   return config.getProperty(key);
	}
}