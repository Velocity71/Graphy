package src;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    // variables related to logging
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static FileHandler logHandler;

    public static void main(String[] args) throws IOException {
        instantiateLogHandler();
        logger.info("Starting Graphy");

        new OutputFrame();

        logHandler.close();
    }

    // instantiate the log file handler
	private static void instantiateLogHandler() throws IOException {
	   try {
			logHandler = new FileHandler("Graphy.log", true); // logs append to file "Graphy.log"
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
}