package me.august.battlefield;

import me.august.battlefield.exception.ParsingException;
import me.august.battlefield.game.Match;
import me.august.battlefield.game.MatchLoader;
import me.august.battlefield.game.RotationManager;
import me.august.battlefield.guns.Gun;
import me.august.battlefield.guns.GunFactory;
import me.august.battlefield.guns.KitItem;
import me.august.battlefield.listener.ConnectionListener;
import me.august.battlefield.util.Log;
import me.august.battlefield.util.XMLUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattlefieldPlugin extends JavaPlugin {

	private static BattlefieldPlugin instance;

	private Match currentMatch;
	private List<KitItem> availableItems;
	private RotationManager rotationManager;

	@Override
	public void onEnable() {
		instance = this;

		availableItems = new ArrayList<>();

		Document xml = null;
		Log.info("Loading gun XML file");
		try {
			xml = new SAXReader().read(getClassLoader().getResourceAsStream("guns.xml"));
		} catch(DocumentException e) {
			Log.warning("Unable to parse gun XML file:");
			e.printStackTrace();
		}

		if(xml != null) {
			for(Element gunElement : XMLUtils.getElements(xml.getRootElement(), "gun")) {
				try {
					Gun gun = GunFactory.build(gunElement);
					availableItems.add(gun);
					Log.info("Gun parsed: " + gun.toString());
				} catch(ParsingException e) {
					Log.warning("Unable to parse gun:");
					e.printStackTrace();
				}
			}
		}

		Log.info(String.valueOf(availableItems.size()) + " guns have been loaded");

		if(!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}

		if(!checkDataFile("config.yml") || !checkDataFile("rotation.yml")) {
			Log.warning("Please restart the server with values in configuration files");
			disable();
			return;
		}

		YamlConfiguration rotationYaml = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "rotation.yml"));
		if(rotationYaml.get("rotation") == null || rotationYaml.getStringList("rotation").size() < 1) {
			Log.warning("Please add values to the rotation.yml file");
			disable();
			return;
		}

		rotationManager = new RotationManager(rotationYaml.getStringList("rotation"));

		Log.info("Maps in rotation:");
		for(String s : rotationManager.getMapNames()) {
			Log.info(s);
		}

		Log.info("Loading match: " + rotationManager.getCurrent());
		try {
			currentMatch = MatchLoader.load(rotationManager.getCurrent());
		} catch(Exception e) {
			Log.warning("Failed to load map: " + rotationManager.getCurrent());
			e.printStackTrace();
		}

		registerListeners();

	}

	@Override
	public void onDisable() {

	}

	private void registerListeners() {
		registerListener(new ConnectionListener());
	}

	private boolean checkDataFile(String name) {
		File file = new File(getDataFolder(), name);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException e) {
				Log.warning("Failed to create file in data folder: " + name);
			}
			return false;
		}
		return true;
	}

	private void disable() {
		getPluginLoader().disablePlugin(this);
	}

	public List<KitItem> getAvailableItems() {
		return availableItems;
	}

	public List<KitItem> getAvailableItems(BattlefieldClass bfClass) {
		List<KitItem> items = new ArrayList<>();
		for(KitItem i : availableItems) {
			if(i.getBattlefieldClass() == bfClass) items.add(i);
		}
		return items;
	}

	public List<KitItem> getAvailableItems(BattlefieldClass... classes) {
		List<BattlefieldClass> classList = Arrays.asList(classes);
		List<KitItem> items = new ArrayList<>();
		for(KitItem i : availableItems) {
			if(classList.contains(i.getBattlefieldClass())) items.add(i);
		}
		return items;
	}

	public RotationManager getRotationManager() {
		return rotationManager;
	}

	public static BattlefieldPlugin get() {
		return instance;
	}

	public static Match getCurrentMatch() {
		return get().currentMatch;
	}

	public static void registerListener(Listener listener) {
		get().getServer().getPluginManager().registerEvents(listener, get());
	}

	public static Document loadFromResources(String fileName) throws Exception {
		return new SAXReader().read(get().getClassLoader().getResourceAsStream(fileName));
	}

}
