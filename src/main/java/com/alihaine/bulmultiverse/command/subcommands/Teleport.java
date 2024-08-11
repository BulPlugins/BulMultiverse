package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class Teleport implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("ONLY IN GAME");
            return;
        }

        Player player = (CraftPlayer) sender;
        try {
            World world = Bukkit.getWorld(args.get(0));
            player.teleport(world.getSpawnLocation());
        } catch (NullPointerException exception) {
            player.sendMessage("MSG WORLD DONT EXIST");
        } catch (ArrayIndexOutOfBoundsException exception) {
            player.sendMessage("MSG NO WORLD SPECIFIED");
        }
    }
}
