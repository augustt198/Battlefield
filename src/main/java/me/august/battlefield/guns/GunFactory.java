package me.august.battlefield.guns;

import static me.august.battlefield.util.ParsingUtils.*;

import me.august.battlefield.BattlefieldClass;
import me.august.battlefield.exception.ParsingException;
import org.dom4j.Element;

public class GunFactory {

	public static Gun build(Element element) throws ParsingException {
		validateAttributes(element, "name", "type");
		validateElements(element, "firerate", "bullets", "magazines", "material", "maxdamage");

		if(ItemType.get(element.attributeValue("type")) == null) throw new ParsingException("Type cannot be found" +
				element.attributeValue("type"));


		BattlefieldClass gunClass = BattlefieldClass.ALL;
		if(element.element("kit") != null) {
			gunClass = BattlefieldClass.get(element.element("kit").getText());
		}
		gunClass = gunClass == null ? BattlefieldClass.ALL : gunClass;

		return new Gun(
				element.attributeValue("name"),
				gunClass,
				parseMaterial(element.element("material").getText()),
				ItemType.get(element.attributeValue("type")),
				parseInt(element.element("magazines").getText()),
				parseInt(element.element("bullets").getText()),
				parseInt(element.element("firerate").getText()),
				parseDouble(element.element("accuracy").getText()),
				parseDouble(element.element("maxdamage").getText())
		);

	}

}
