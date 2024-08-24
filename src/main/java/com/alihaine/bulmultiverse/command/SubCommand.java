package com.alihaine.bulmultiverse.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {
    void executor(CommandSender sender, List<String> args);
    String getUsage();
    String getDescription();
}
