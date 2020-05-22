package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	
	private Log() {
	    throw new IllegalStateException("Utility class");
	} 
	
	public static final Logger logger = Logger.getLogger("my.logger");

	public static final void initLogger() {
		logger.setLevel(Level.ALL);
	}
	
}
