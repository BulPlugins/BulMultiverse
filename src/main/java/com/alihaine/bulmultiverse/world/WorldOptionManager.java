package com.alihaine.bulmultiverse.world;

import com.alihaine.bulmultiverse.options.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.*;

public class WorldOptionManager {
    private final List<WorldOption> availableOptions = new ArrayList<>();

    public void loadDefaultOption() {
        new Environment();
        new Seed();
        new Structures();
        new Type();
        new Difficulty();
        new Pvp();
    }

    public boolean isWorldFolderExisting(String worldName) {
        File worldFolder = new File(Bukkit.getServer().getWorldContainer(), worldName);
        return worldFolder.exists() && worldFolder.isDirectory();
    }

    public WorldOption getOption(String optionAsString) throws Exception {
        for (WorldOption option : availableOptions) {
            if (option.matches(optionAsString))
                return option;
        }
        throw new Exception("§cOption " + optionAsString + " not found or not supported");
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

    public void addNewOption(WorldOption option) {
        this.availableOptions.add(option);
    }
}
