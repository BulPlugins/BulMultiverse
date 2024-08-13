package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.options.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WorldOptionManager {
    protected HashMap<String, WorldOption> availableOptions = new HashMap<>();

    public WorldOptionManager() {
        availableOptions.put("environment", new Environment());
        availableOptions.put("seed", new Seed());
        availableOptions.put("structures", new Structures());
        availableOptions.put("type", new Type());
        availableOptions.put("difficulty", new Difficulty());
        availableOptions.put("pvp", new Pvp());
    }

    public boolean isWorldFolderExisting(String worldName) {
        File worldFolder = new File(Bukkit.getServer().getWorldContainer(), worldName);
        return worldFolder.exists() && worldFolder.isDirectory();
    }

    public WorldOption getOption(String optionAsString) throws Exception {
        WorldOption option = availableOptions.get(optionAsString);
        if (option == null)
            throw new Exception("§cOption " + optionAsString + " not found or not supported");
        return option;
    }

    public String buildOptionString(String optionFromCmd) throws Exception {
        switch (optionFromCmd) {
            case "-e":
                return "environment";
            case "-s":
                return "seed";
            case "-b":
                return "structures";
            case "-t":
                return "type";
            case "-d":
                return "difficulty";
            case "-p":
                return "pvp";
        }
        throw new Exception("§cThe flag " + optionFromCmd + " don't exist, check with §e/bmv flags");
    }

    public void createWorldWithMap(CommandSender sender, String name, Map<WorldOption, String> options) {
        WorldCreator newWorldCreator = new WorldCreator(name);

        Iterator<Map.Entry<WorldOption, String>> iterator = options.entrySet().iterator();
        sender.sendMessage("§e[BULMultiverse] §aStart the creation and loading of the world: §2" + name);
        while (iterator.hasNext()) {
            Map.Entry<WorldOption, String> entry = iterator.next();
            if (entry.getKey().isWorldRequired())
                continue;
            try {
                entry.getKey().optionExecutor(entry.getValue(), newWorldCreator);
            } catch (Exception exception){
                sender.sendMessage("§c" + exception.getMessage());
            }
            iterator.remove();
        }

        World newWorld = newWorldCreator.createWorld();

        iterator = options.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<WorldOption, String> entry = iterator.next();
            try {
                entry.getKey().optionExecutor(entry.getValue(), newWorld);
            } catch (Exception exception){
                sender.sendMessage("§c" + exception.getMessage());
            }
            iterator.remove();
        }
        sender.sendMessage("§e[BULMultiverse] §aworld: §2" + name + " §aloaded.");
    }
}
