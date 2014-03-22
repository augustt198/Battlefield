package me.august.bf4.guns;

import static me.august.bf4.util.ParsingUtils.*;

import me.august.bf4.exception.ParsingException;
import org.dom4j.Element;

public class GunFactory {

	public static Gun build(Element element) throws ParsingException {
		validateAttributes(element, "name", "type");
		validateElements(element, "firerate", "bullets", "magazines", "material");

		if(GunType.get(element.attributeValue("type")) == null) throw new ParsingException("Type cannot be found" +
				element.attributeValue("tyoe"));

		return new Gun(
				element.attributeValue("name"),
				parseMaterial(element.element("material").getText()),
				GunType.get(element.attributeValue("type")),
				parseInt(element.element("magazines").getText()),
				parseInt(element.element("bullets").getText()),
				parseInt(element.element("firerate").getText()),
				parseDouble(element.element("accuracy").getText())
		);

	}

}
