package me.august.battlefield.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by August on 3/22/14.
 */
public class Log {

	private static Logger logger = Bukkit.getLogger();

	private static String PREFIX = "[Battlefield] ";

	public static void info(String msg) {
		logger.info(PREFIX + msg);
	}

	public static void warning(String msg) {
		logger.warning(PREFIX + msg);
	}

	public static void severe(String msg) {
		logger.severe(PREFIX + msg);
	}

	public static void log(Level level, String msg) {
		logger.log(level, PREFIX + msg);
	}

}
