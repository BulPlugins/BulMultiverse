package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Flags implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        sender.sendMessage("§e-e <environment> §8| Environment such as nether, normal..");
        sender.sendMessage("§e-s <seed> §8| Seed of the world");
        sender.sendMessage("§e-d <difficulty> §8| Difficulty such as easy, normal..");
        sender.sendMessage("§e-b <true or false> §8| Enable default build such as village");
        sender.sendMessage("§e-t <type> §8| Type such as flat, amplified..");
        sender.sendMessage("§e-p <true or false> §8| Enable pvp");
        sender.sendMessage("§eMore help here: §ahttps://github.com/AliHaine/BulMultiverse");
    }
}
