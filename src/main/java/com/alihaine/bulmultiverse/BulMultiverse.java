package com.alihaine.bulmultiverse;

import co.aikar.commands.PaperCommandManager;
import com.alihaine.bulmultiverse.addon.AddonManager;
import com.alihaine.bulmultiverse.addon.BulMultiverseAddon;
import com.alihaine.bulmultiverse.command.*;
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

    public static final String line = "------------------------------------------------------------------";

    private static BulMultiverse bulMultiverse;
    private WorldsFile worldsFile;
    private WorldOptionManager worldOptionManager;
    private WorldDataManager worldDataManager;
    private AddonManager addonManager;
    private PaperCommandManager commandManager;


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

        addonManager = new AddonManager();

        addonManager.runAddonsAction(BulMultiverseAddon::onEnable);

        worldsFile = new WorldsFile(this);
        worldsFile.extractWorldsFromFile();

        this.setupDefaultCommands();
        addonManager.runAddonsAction(BulMultiverseAddon::onEnableAfterWorldsLoad);

        getLogger().info("Enable");
    }

    @Override
    public void onDisable() {
        addonManager.runAddonsAction(BulMultiverseAddon::onDisable);
        getLogger().info("Disable");
    }

    private void setupDefaultCommands() {
        commandManager = new PaperCommandManager(this);
        commandManager.enableUnstableAPI("help");
        commandManager.registerCommand(new AddonsCommand());
        commandManager.registerCommand(new InfoCommand());
        commandManager.registerCommand(new ListCommand());
        commandManager.registerCommand(new TeleportCommand());
        commandManager.registerCommand(new LoadCommand());
        commandManager.registerCommand(new UnloadCommand());
        commandManager.registerCommand(new CreateCommand());
        commandManager.registerCommand(new SetCommand());
        commandManager.registerCommand(new FlagsCommand());
        commandManager.registerCommand(new HelpCommand());
    }

    private void updateChecker() {
        try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=118884").openStream(); Scanner scanner = new Scanner(inputStream)) {
            if (scanner.next().equals(this.getDescription().getVersion()))
                return;
            getLogger().info(BulMultiverse.line);
            getLogger().info("There is a new update available for BulMultiverse !");
            getLogger().info("Download here : https://www.spigotmc.org/resources/118884");
            getLogger().info(BulMultiverse.line);
        } catch (IOException exception) {
            getLogger().info("[BulMultiverse] Cannot look for updates please join discord: https://discord.gg/wxnTV68dX2" + exception.getMessage());
        }
    }

    public static BulMultiverse getBulMultiverse() {
        return bulMultiverse;
    }

    public WorldsFile getWorldsFile() {
        return worldsFile;
    }

    public WorldOptionManager getWorldOptionManager() {
        return worldOptionManager;
    }

    public WorldDataManager getWorldDataManager() {
        return worldDataManager;
    }

    public AddonManager getAddonManager() { return addonManager; }

    /*
     * Addons must use this function to register additional commands.
     */
    public void registerCommand(BaseBmvCommand newCommand) {
        commandManager.registerCommand(newCommand);
    }
}
