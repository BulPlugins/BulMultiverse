package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.options.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

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

    public WorldOption getOption(String optionAsString) throws Exception {
        WorldOption option = availableOptions.get(optionAsString);
        if (option == null)
            throw new Exception();
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
        throw new Exception();
    }

    public void createWorldWithMap(String name, Map<WorldOption, String> options) {
        WorldCreator newWorldCreator = new WorldCreator(name);

        Iterator<Map.Entry<WorldOption, String>> iterator = options.entrySet().iterator();
        Bukkit.getConsoleSender().sendMessage("----------------------------");
        Bukkit.getConsoleSender().sendMessage("Start creation of " + name);
        while (iterator.hasNext()) {
            Map.Entry<WorldOption, String> entry = iterator.next();
            if (entry.getKey().isWorldRequired()) {
                System.out.println("need world for " + entry.getValue());
                continue;
            }
            entry.getKey().optionExecutor(entry.getValue(), newWorldCreator);
            iterator.remove();
        }
        World newWorld = newWorldCreator.createWorld();

        Bukkit.getConsoleSender().sendMessage("World is created " + name);

        iterator = options.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<WorldOption, String> entry = iterator.next();
            entry.getKey().optionExecutor(entry.getValue(), newWorld);
            iterator.remove();
        }
        Bukkit.getConsoleSender().sendMessage("----------------------------");
    }
}
