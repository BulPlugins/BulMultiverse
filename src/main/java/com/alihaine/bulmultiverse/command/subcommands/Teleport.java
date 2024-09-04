package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Teleport implements SubCommand {

    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) {
            new Message("only_ingame_command").sendMessage(sender);
            return;
        }
        if (args.isEmpty()) {
            new Message("no_world_target").sendMessage(sender);
            return;
        }

        Player player = (Player) sender;
        World world = Bukkit.getWorld(args.get(0));
        if (world == null) {
            new Message("world_not_found").withPlaceHolder("name", args.get(0)).sendMessage(sender);
        } else {
            player.teleport(world.getSpawnLocation());
            new Message("cmd_teleport_success").withPlaceHolder("name", world.getName()).sendMessage(sender);
        }
    }

    @Override
    public String getUsage() {
        return "/bmv tp <world_name>";
    }

    @Override
    public String getDescription() {
        return "Teleport to the target world";
    }
}
