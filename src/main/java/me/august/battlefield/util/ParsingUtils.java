package me.august.battlefield.util;

import me.august.battlefield.exception.ParsingException;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class ParsingUtils {

	public static Material parseMaterial(String name) throws ParsingException {
		try {
			return Material.valueOf(name.toUpperCase().replace(" ", "_"));
		} catch(IllegalArgumentException e) {
			throw new ParsingException("Material not found: " + name);
		}
	}

	public static int parseInt(String number) throws ParsingException {
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) {
			throw new ParsingException("Cannot coerce into integer: " + number);
		}
	}

	public static double parseDouble(String number) throws ParsingException {
		try {
			return Double.parseDouble(number);
		} catch(NumberFormatException e) {
			throw new ParsingException("Cannot coerce into integer: " + number);
		}
	}

	public static ChatColor parseColor(String color) throws ParsingException {
		try {
			return ChatColor.valueOf(color.toUpperCase().replace(" ", "_"));
		} catch(IllegalArgumentException e) {
			throw new ParsingException("ChatColor not found: " + color);
		}
	}

	public static Vector parseVector(String vector) throws ParsingException {
		String[] coords = vector.replace(" ", "").split(",");
		if(coords.length != 3) throw new ParsingException("Vector must consist of 3 coordinates");
		return new Vector(parseDouble(coords[0]), parseDouble(coords[1]), parseDouble(coords[2]));
	}

	public static void validateAttributes(Element e, String... attributes) throws ParsingException {
		List<String> missing = new ArrayList<>();
		for(String s : attributes) {
			if(e.attributeValue(s) == null) missing.add(s);
		}
		if(missing.size() > 0) throw new ParsingException("Required attributes not found: " + StringUtils.join(missing, ", "));
	}

	public static void validateElements(Element e, String... tags) throws ParsingException {
		List<String> missing = new ArrayList<>();
		for(String s : tags) {
			if(e.element(s) == null) missing.add(s);
		}
		if(missing.size() > 0) throw new ParsingException("Required elements not found: " + StringUtils.join(missing, ", "));
	}

	public static String simpleCoordsString(Vector vec) {
		return String.valueOf(vec.getBlockX()) + ", " + String.valueOf(vec.getBlockY()) + ", " + String.valueOf(vec.getBlockZ());
	}

}
