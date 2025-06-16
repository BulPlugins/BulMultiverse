package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.Default;
import org.bukkit.command.CommandSender;

public class BaseBmvCommand extends BaseCommand {
    @Default
    public void onDefault(CommandSender sender) {
        sender.sendMessage("§cMissing arguments. Use /<cmd> help for info.");
    }
}
