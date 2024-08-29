package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.message.Message;
import com.alihaine.bulmultiverse.message.MessageType;
import com.alihaine.bulmultiverse.message.PlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Teleport implements SubCommand {

    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) {
            new Message(MessageType.ONLY_INGAME_COMMAND).sendMessage(sender);
            return;
        }
        if (args.isEmpty()) {
            new Message(MessageType.NO_WORLD_TARGET).sendMessage(sender);
            return;
        }

        Player player = (Player) sender;
        World world = Bukkit.getWorld(args.get(0));
        if (world == null) {
            new Message(MessageType.WORLD_NOT_FOUND).withPlaceHolder(PlaceHolder.NAME, args.get(0)).sendMessage(sender);
        } else {
            player.teleport(world.getSpawnLocation());
            new Message(MessageType.CMD_TELEPORT_SUCCESS).withPlaceHolder(PlaceHolder.NAME, world.getName()).sendMessage(sender);
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
