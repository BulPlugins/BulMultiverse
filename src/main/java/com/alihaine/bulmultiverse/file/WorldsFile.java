package com.alihaine.bulmultiverse.file;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.WorldOption;
import com.alihaine.bulmultiverse.WorldOptionManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorldsFile {
    private File file;
    private FileConfiguration fileConfiguration;
    private final WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    public WorldsFile(BulMultiverse bulMultiverseInstance) {
        file = new File(bulMultiverseInstance.getDataFolder(), "worlds.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            bulMultiverseInstance.saveResource("worlds.yml", false);
        }

        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Bukkit.getConsoleSender().sendMessage("[BulMultiverse] Error with the worlds saver file");
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[BulMultiverse] Error when trying to save worlds to file");
        }
    }

    public void saveWorldsToFile() {
        for (World world: Bukkit.getWorlds()) {
            WorldData worldData = new WorldData(world);
            fileConfiguration.createSection("worlds." + world.getName(), worldData.dumpsForSave());
        }
        saveFile();
    }

    public void removeWorldFromFile(String worldName) {
        fileConfiguration.set("worlds." + worldName, null);
        saveFile();
        Bukkit.getConsoleSender().sendMessage("§eThe world " + worldName + " is removed from worlds.yml file.");
    }

    public void extractWorldsFromFile() {
        ConfigurationSection worldsSection = fileConfiguration.getConfigurationSection("worlds");
        if (worldsSection == null)
            return;

        Map<WorldOption, String> convertToOptionString = new HashMap<>();

        for (String worldName : worldsSection.getKeys(false)) {
            if (!worldOptionManager.isWorldFolderExisting(worldName)) {
                Bukkit.getConsoleSender().sendMessage("§cThe world " + worldName + " folder don't exist, but are in worlds.yml. Remove from worlds.yml");
                removeWorldFromFile(worldName);
                continue;
            }

            ConfigurationSection worldSection = fileConfiguration.getConfigurationSection("worlds." + worldName);
            worldSection.getValues(false).forEach((key, value) -> {
                try {
                    convertToOptionString.put(worldOptionManager.getOption(key), value.toString());
                } catch (Exception exception) {
                    Bukkit.getConsoleSender().sendMessage(exception.getMessage());
                }
            });
            worldOptionManager.createWorldWithMap(Bukkit.getConsoleSender(), worldName, convertToOptionString);
        }
    }
}
