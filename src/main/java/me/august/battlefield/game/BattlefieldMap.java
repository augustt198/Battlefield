package me.august.battlefield.game;


import org.dom4j.Document;

/**
 * Created by August on 3/23/14.
 */
public class BattlefieldMap {

	protected String mapName;

	protected Document xml;

	public BattlefieldMap(String mapName, Document xml) {
		this.mapName = mapName;
		this.xml = xml;
	}



}
