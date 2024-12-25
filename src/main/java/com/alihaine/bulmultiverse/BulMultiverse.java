package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.file.ConfigFile;
import com.alihaine.bulmultiverse.file.WorldsFile;
import com.alihaine.bulmultiverse.world.WorldDataManager;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class BulMultiverse extends JavaPlugin {

    private static BulMultiverse bulMultiverse;
    private static WorldsFile worldsFile;
    private static WorldOptionManager worldOptionManager;
    private static BMV bmv;
    private static WorldDataManager worldDataManager;
    private final List<BulMultiverseAddon> addons = new ArrayList<>();

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

        loadAddons();

        runAddonOnEnable();

        worldsFile = new WorldsFile(this);
        worldsFile.extractWorldsFromFile();

        runAddonOnEnableAfterWorldsLoad();

        getLogger().info("[BulMultiverse] Enable");
    }

    @Override
    public void onDisable() {
        runAddonOnDisable();
        getLogger().info("§c[BulMultiverse] Disable");
    }

    private void loadAddons() {
        File[] addonsJar = new File(this.getDataFolder() + "/addons").listFiles((dir, name) -> name.endsWith(".jar"));
        if (addonsJar == null)
            return;

        for (File addonFile : addonsJar) {
            try (URLClassLoader loader = new URLClassLoader(new URL[]{addonFile.toURI().toURL()}, this.getClass().getClassLoader())) {
                try (JarFile jarFile = new JarFile(addonFile)) {
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry elem = entries.nextElement();
                        if (!elem.getName().endsWith(".class"))
                            continue;

                        String classToLoad = elem.getName().replace('/', '.').replaceAll(".class", "");
                        try {
                            Class<?> clazz = loader.loadClass(classToLoad);
                            if (clazz.getSuperclass().getName().contains("BulMultiverseAddon")) {
                                BulMultiverseAddon addon = (BulMultiverseAddon) clazz.getDeclaredConstructor().newInstance();
                                addons.add(addon);
                            }
                        } catch (Exception e) {
                            printAddonError(addonFile.getName(), e);
                        }
                    }
                } catch (IOException e) {
                    printAddonError(addonFile.getName(), e);
                }
            } catch (SecurityException | NullPointerException | IOException e) {
                printAddonError(addonFile.getName(), e);
            }
        }
    }

    private void runAddonOnEnable() {
        for (BulMultiverseAddon addon : addons) {
            addon.onEnable();
        }
    }

    private void runAddonOnEnableAfterWorldsLoad() {
        for (BulMultiverseAddon addon : addons) {
            try {
                addon.onEnableAfterWorldsLoad();
            } catch (AbstractMethodError e) {
                this.printAddonError("null", e);
                addons.remove(addon);
                addon.onDisable();
            }
        }
    }

    private void printAddonError(String addonName, Throwable e) {
        getLogger().severe("------------------------------------------------------------------");
        getLogger().severe("An error occurred with the addon " + addonName);
        getLogger().severe("Error type: " + e);
        getLogger().severe("Error details: ");
        e.printStackTrace();
        getLogger().severe("------------------------------------------------------------------");
    }

    private void runAddonOnDisable() {
        for (BulMultiverseAddon addon : addons) {
            addon.onDisable();
        }
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
