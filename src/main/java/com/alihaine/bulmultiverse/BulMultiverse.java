package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.file.ConfigFile;
import com.alihaine.bulmultiverse.file.WorldsFile;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class BulMultiverse extends JavaPlugin {

    private static BulMultiverse bulMultiverse;
    private static ConfigFile configFile;
    private static WorldsFile worldsFile;
    private static WorldOptionManager worldOptionManager;

    @Override
    public void onEnable() {
        updateChecker();
        new Metrics(this, 22989);
        bulMultiverse = this;
        this.saveDefaultConfig();

        configFile = new ConfigFile();

        worldOptionManager = new WorldOptionManager();

        worldsFile = new WorldsFile(this);
        worldsFile.extractWorldsFromFile();

        this.getCommand("bmv").setExecutor(new BMV());
        Bukkit.getConsoleSender().sendMessage("[BulMultiverse] enable");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[BulMultiverse] disable");
    }

    private void updateChecker() {
        try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=118884").openStream(); Scanner scanner = new Scanner(inputStream)) {
            if (!scanner.next().equals(this.getDescription().getVersion())) {
                Bukkit.getConsoleSender().sendMessage("------------------------------------------------------------------");
                Bukkit.getConsoleSender().sendMessage("There is a new update available for BulMultiverse !");
                Bukkit.getConsoleSender().sendMessage("Download here : https://www.spigotmc.org/resources/118884");
                Bukkit.getConsoleSender().sendMessage("------------------------------------------------------------------");
            }
        } catch (IOException exception) {
            this.getLogger().info("[BulMultiverse] Cannot look for updates please join discord: https://discord.gg/wxnTV68dX2" + exception.getMessage());
        }
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
