package me.august.bf4.util;

import me.august.bf4.exception.ParsingException;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
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

}
