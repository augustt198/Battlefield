package me.august.battlefield.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by August on 3/22/14.
 */
public class Log {

	private static Logger logger = Bukkit.getLogger();

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void warning(String msg) {
		logger.warning(msg);
	}

	public static void severe(String msg) {
		logger.severe(msg);
	}

	public static void log(Level level, String msg) {
		logger.log(level, msg);
	}

}
