package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Teleport implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§e[BULMultiverse] §cThis command can be executed only in-game");
            return;
        }

        Player player = (Player) sender;
        try {
            World world = Bukkit.getWorld(args.get(0));
            player.teleport(world.getSpawnLocation());
            player.sendMessage("§e[BULMultiverse] §aSuccessfully teleported to the world: " + world.getName());
        } catch (NullPointerException exception) {
            player.sendMessage("§cThe world: §e" + args.get(0) + " §cdon't exist or are not loaded.");
        } catch (ArrayIndexOutOfBoundsException exception) {
            player.sendMessage("§cPlease specify a world. Check commands usage with /bmv help or in the wiki.");
        }
    }
}
