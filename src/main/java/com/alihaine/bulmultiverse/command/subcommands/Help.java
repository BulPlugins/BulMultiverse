package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Help implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        sender.sendMessage("§e/bmv create <world_name> (options) §8| Create a new world");
        sender.sendMessage("§e/bmv load <world_name> §8| Load existing world");
        sender.sendMessage("§e/bmv list §8| List all the loaded worlds");
        sender.sendMessage("§e/bmv tp <world_name> §8| Teleport to the target world");
        sender.sendMessage("§e/bmv set <world_name> <option> §8| Set a option to the target world");
        sender.sendMessage("§e/bmv flags §8| See all available flags");
        sender.sendMessage("§eMore help here: §ahttps://github.com/AliHaine/BulMultiverse");
    }
}
