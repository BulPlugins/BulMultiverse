package com.alihaine.bulmultiverse.command;

import org.bukkit.command.CommandSender;

public interface SubCommand {
    void executor(CommandSender sender, String[] args);
}
