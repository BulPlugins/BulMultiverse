package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.addon.AddonManager;
import com.alihaine.bulmultiverse.addon.BulMultiverseAddon;
import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.file.ConfigFile;
import com.alihaine.bulmultiverse.file.WorldsFile;
import com.alihaine.bulmultiverse.world.WorldDataManager;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class BulMultiverse extends JavaPlugin {

    private static BulMultiverse bulMultiverse;
    private static WorldsFile worldsFile;
    private static WorldOptionManager worldOptionManager;
    private static BMV bmv;
    private static WorldDataManager worldDataManager;
    private AddonManager addonManager;

    @Override
    public void onEnable() {
        updateChecker();
        new Metrics(this, 22989);
        bulMultiverse = this;
        this.saveDefaultConfig();
        ConfigFile.bulMultiverse = this;

        worldDataManager = new WorldDataManager();
        worldOptionManager = new WorldOptionManager();
        worldOptionManager.loadDefaultOption();

        bmv = new BMV();
        this.getCommand("bmv").setExecutor(bmv);
        bmv.loadDefaultCommands();

        addonManager = new AddonManager();

        addonManager.runAddonsAction(BulMultiverseAddon::onEnable);

        worldsFile = new WorldsFile(this);
        worldsFile.extractWorldsFromFile();

        addonManager.runAddonsAction(BulMultiverseAddon::onEnableAfterWorldsLoad);

        getLogger().info("[BulMultiverse] Enable");
    }

    @Override
    public void onDisable() {
        addonManager.runAddonsAction(BulMultiverseAddon::onDisable);
        getLogger().info("[BulMultiverse] Disable");
    }

    private void updateChecker() {
        try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=118884").openStream(); Scanner scanner = new Scanner(inputStream)) {
            if (!scanner.next().equals(this.getDescription().getVersion())) {
                getLogger().info("------------------------------------------------------------------");
                getLogger().info("There is a new update available for BulMultiverse !");
                getLogger().info("Download here : https://www.spigotmc.org/resources/118884");
                getLogger().info("------------------------------------------------------------------");
            }
        } catch (IOException exception) {
            getLogger().info("[BulMultiverse] Cannot look for updates please join discord: https://discord.gg/wxnTV68dX2" + exception.getMessage());
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
