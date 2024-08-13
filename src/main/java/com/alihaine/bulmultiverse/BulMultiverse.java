package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.file.ConfigFile;
import com.alihaine.bulmultiverse.file.WorldsFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BulMultiverse extends JavaPlugin {

    private static BulMultiverse bulMultiverse;
    private static ConfigFile configFile;
    private static WorldsFile worldsFile;
    private static WorldOptionManager worldOptionManager;

    @Override
    public void onEnable() {
        bulMultiverse = this;
        this.saveDefaultConfig();

        configFile = new ConfigFile();

        worldOptionManager = new WorldOptionManager();

        worldsFile = new WorldsFile(this);
        worldsFile.extractWorldsFromFile();

        this.getCommand("bmv").setExecutor(new BMV());
        System.out.println("[BulMultiverse] enable");
    }

    @Override
    public void onDisable() {
        System.out.println("[BulMultiverse] disable");
    }

    public static BulMultiverse getBulMultiverseInstance() {
        return bulMultiverse;
    }

    public static ConfigFile getConfigFileInstance() {
        return configFile;
    }


    public static WorldsFile getWorldsFileInstance() {
        return worldsFile;
    }

    public static WorldOptionManager getWorldOptionManager() {
        return worldOptionManager;
    }
}
