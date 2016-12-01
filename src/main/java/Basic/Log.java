package Basic;

import org.apache.log4j.Logger;

public class Log {
	private static Logger Log = Logger.getLogger(Log.class.getName());

	public static void startTestCase(String testCaseName) {
		Log.info("-------- \"" + testCaseName + "\"¿ªÊ¼Ö´ÐÐ ------");
	}

	public static void endTestCase(String testCaseName) {
		Log.info("-------- \"" + testCaseName + "\"²âÊÔÖ´ÐÐ½áÊø ------");
	}

	public static void info(String message) {
		Log.info(message);
	}

	public static void error(String message) {
		Log.info(message);
	}

	public static void debug(String message) {
		Log.info(message);
	}
}