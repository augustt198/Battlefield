package me.august.bf4.util;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class XMLUtils {

	public static List<Element> getElements(Element e) {
		try {
			return (List<Element>) e.elements();
		} catch(ClassCastException ex) {
			return new ArrayList<>();
		}
	}

	public static List<Element> getElements(Element e, String name) {
		try {
			return (List<Element>) e.elements(name);
		} catch(ClassCastException ex) {
			return new ArrayList<>();
		}
	}

}
