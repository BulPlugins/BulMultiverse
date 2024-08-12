package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.file.WorldsFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BulMultiverse extends JavaPlugin {

    private static BulMultiverse bulMultiverse;
    private static WorldsFile worldsFile;
    private static WorldOptionManager worldOptionManager;

    @Override
    public void onEnable() {
        bulMultiverse = this;

        worldOptionManager = new WorldOptionManager();

        worldsFile = new WorldsFile(this);
        worldsFile.extractWorldsFromFile();

        this.getCommand("bmv").setExecutor(new BMV());
        System.out.println("[BULMultiverse enable]");
    }

    @Override
    public void onDisable() {
        System.out.println("[BULMultiverse disable]");
    }

    public static BulMultiverse getBulMultiverseInstance() {
        return bulMultiverse;
    }

    public static WorldsFile getWorldsFileInstance() {
        return worldsFile;
    }

    public static WorldOptionManager getWorldOptionManager() {
        return worldOptionManager;
    }
}
