package com.alihaine.bulmultiverse.addon;

import com.alihaine.bulmultiverse.BulMultiverse;
import org.bukkit.Bukkit;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AddonManager {

    private final List<BulMultiverseAddon> addons = new ArrayList<>();

    public AddonManager() {
        HashMap<File, URL> jarFileAndURL = setupJarAndURL(new File(BulMultiverse.getBulMultiverseInstance().getDataFolder() + "/addons").listFiles((dir, name) -> name.endsWith(".jar")));
        if (jarFileAndURL != null)
            loadAddons(jarFileAndURL);
    }

    public HashMap<File, URL> setupJarAndURL(File[] files) {
        if (files == null)
            return null;

        HashMap<File, URL> jarFileAndURL = new HashMap<>();
        for (File file : files)
            jarFileAndURL.put(file, null);
        return jarFileAndURL;
    }

    public void loadAddons(HashMap<File, URL> jarFileAndURL) {
        for (Map.Entry<File, URL> entry : jarFileAndURL.entrySet()) {
            try {
                entry.setValue(entry.getKey().toURI().toURL());
            } catch (Exception e) {
                printAddonError(entry.getKey().getName(), e);
            }
        }

        URLClassLoader loader = setupURLClassLoader(jarFileAndURL);

        for (Map.Entry<File, URL> entry : jarFileAndURL.entrySet()) {
            if (entry.getValue() == null)
                continue;

            try (JarFile jarFile = new JarFile(entry.getKey())) {
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry elem = entries.nextElement();
                    if (!elem.getName().endsWith(".class"))
                        continue;

                    String classToLoad = elem.getName().replace('/', '.').replaceAll(".class", "");
                    try {
                        Class<?> clazz = Class.forName(classToLoad, true, loader);
                        if (clazz.getSuperclass().getName().contains("BulMultiverseAddon")) {
                            System.out.println(jarFile.getName() + classToLoad);
                            BulMultiverseAddon addon = (BulMultiverseAddon) clazz.getDeclaredConstructor().newInstance();
                            addons.add(addon);
                            break;
                        }
                    } catch (Exception | Error e) {
                        printAddonError(jarFile.getName(), e);
                    }
                }
            } catch (Exception | Error e) {
                printAddonError(entry.getKey().getName(), e);
            }
        }
    }

    private URLClassLoader setupURLClassLoader(HashMap<File, URL> jarFileAndURL) {
        List<URL> urls = new ArrayList<>();

        jarFileAndURL.forEach((key, value) -> {
            if (value != null)
                urls.add(value);
        });

        return new URLClassLoader(urls.toArray(new URL[0]), BulMultiverse.getBulMultiverseInstance().getClass().getClassLoader());
    }

    public void runAddonsAction(AddonAction addonAction) {
        List<BulMultiverseAddon> addonsToRemove = new ArrayList<>();

        for (BulMultiverseAddon addon : addons) {
            try {
                addonAction.execute(addon);
            } catch (Exception | Error e) {
                this.printAddonError("null", e);
                addonsToRemove.add(addon);
            }
        }

        for (BulMultiverseAddon addon : addonsToRemove)
            this.addonRemove(addon);
    }

    private void printAddonError(String addonName, Throwable e) {
        Bukkit.getLogger().warning("------------------------------------------------------------------");
        Bukkit.getLogger().severe("An error occurred with the addon " + addonName);
        Bukkit.getLogger().severe("Error type: " + e);
        Bukkit.getLogger().severe("Error details: ");
        e.printStackTrace();
        Bukkit.getLogger().warning("------------------------------------------------------------------");
    }

    private void addonRemove(BulMultiverseAddon addon) {
        addons.remove(addon);
        addon.onDisable();
    }
}
