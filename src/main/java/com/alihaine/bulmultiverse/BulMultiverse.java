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

        worldsFile = new WorldsFile(this);
        worldsFile.createWorldsFromFile();

        worldOptionManager = new WorldOptionManager();

        this.getCommand("bmv").setExecutor(new BMV());
        System.out.println("enable");
    }

    @Override
    public void onDisable() {
        System.out.println("disable");
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
