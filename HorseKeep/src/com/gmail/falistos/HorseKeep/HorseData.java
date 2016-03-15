package com.gmail.falistos.HorseKeep;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class HorseData {

	private String horsesDataFilePath = null;
	private FileConfiguration horsesData = null;
	private File horsesDataFile = null;
	
	private final Plugin plugin;
	
	public HorseData(Plugin plugin, String path) {
		this.horsesDataFilePath = path;
		this.plugin = plugin;
		this.reload();
	}
	
	public void reload() {
	    if (horsesDataFile == null) {
	    	horsesDataFile = new File(plugin.getDataFolder(), horsesDataFilePath);
	    }
	    horsesData = YamlConfiguration.loadConfiguration(horsesDataFile);

	    InputStream defConfigStream = plugin.getResource(horsesDataFilePath);
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        horsesData.setDefaults(defConfig);
	    }
	    
	    this.save();
	}
	
	public FileConfiguration getHorsesData() {
	    if (horsesData == null) {
	        this.reload();
	    }
	    return horsesData;
	}
	
	public void save() {
	    if (horsesData == null || horsesDataFile == null) {
	    	return;
	    }
	    try {
	        getHorsesData().save(horsesDataFile);
	    } catch (IOException ex) {
	    	plugin.getLogger().log(Level.SEVERE, "Could not save horse data to " + horsesDataFile, ex);
	    }
	}
	
}
