package com.alihaine.bulmultiverse.command.subcommands;

import org.bukkit.WorldCreator;

public interface Option {
    void optionExecutor(String value, WorldCreator worldCreator);
}
