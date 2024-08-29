package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.file.ConfigFile;
import com.alihaine.bulmultiverse.file.WorldsFile;
import com.alihaine.bulmultiverse.world.WorldDataManager;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class BulMultiverse extends JavaPlugin {

    private static BulMultiverse bulMultiverse;
    private static WorldsFile worldsFile;
    private static WorldOptionManager worldOptionManager;
    private static BMV bmv;
    private static WorldDataManager worldDataManager;

    @Override
    public void onEnable() {
        updateChecker();
        new Metrics(this, 22989);
        bulMultiverse = this;
        this.saveDefaultConfig();
        ConfigFile.bulMultiverse = this;

        worldDataManager = new WorldDataManager();
        worldOptionManager = new WorldOptionManager();
        worldOptionManager.loadDefaultOption();;

        bmv = new BMV();
        this.getCommand("bmv").setExecutor(bmv);
        bmv.loadDefaultCommands();

        loadAddons();

        worldsFile = new WorldsFile(this);
        worldsFile.extractWorldsFromFile();

        Bukkit.getConsoleSender().sendMessage("§e[BulMultiverse] §aenable");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§c[BulMultiverse] disable");
    }

    private void loadAddons() {
        File addonsFolder = new File(this.getDataFolder() + "/addons");
        if(!addonsFolder.exists()) {
            addonsFolder.mkdir();
            saveResource("addons/how_to_use.yml", false);
            return;
        }

        File[] addonsJar = addonsFolder.listFiles((dir, name) -> name.endsWith(".jar"));
        if (addonsJar == null)
            return;

        for (File addonFile : addonsJar) {
            String jarName = addonFile.getName().replace(".jar", "");
            try {
                URLClassLoader loader = new URLClassLoader(new URL[]{addonFile.toURI().toURL()}, this.getClass().getClassLoader());
                Class<?> addonMainClass = Class.forName("com.alihaine." + jarName.toLowerCase() + "." + jarName, true, loader);
                Object addonInstance = addonMainClass.getDeclaredConstructor().newInstance();
                if (addonInstance instanceof BulMultiverseAddon) {
                    BulMultiverseAddon addon = (BulMultiverseAddon) addonInstance;
                    addon.onEnable();
                }
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§cException when trying to load the addon: §e" + addonFile.getName());
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
            } catch (Error e) {
                Bukkit.getConsoleSender().sendMessage("§cSerious error when trying to load the addon: §e" + addonFile.getName());
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
            }
        }
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

    public static WorldsFile getWorldsFileInstance() {
        return worldsFile;
    }

    public static WorldOptionManager getWorldOptionManager() {
        return worldOptionManager;
    }

    public static BMV getBMVInstance() {
        return bmv;
    }

    public static WorldDataManager getWorldDataManager() {
        return worldDataManager;
    }
}
