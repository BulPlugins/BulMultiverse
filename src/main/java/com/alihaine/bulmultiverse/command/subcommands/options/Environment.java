package com.alihaine.bulmultiverse.command.subcommands.options;

import com.alihaine.bulmultiverse.command.subcommands.Option;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class Environment implements Option {
    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        System.out.println("set env");
        worldCreator.environment(World.Environment.valueOf(value.toUpperCase()));
    }
}
