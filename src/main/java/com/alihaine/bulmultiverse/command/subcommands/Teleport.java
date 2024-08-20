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
            Message.ONLY_INGAME_COMMAND.sendMessage(sender);
            return;
        }

        Player player = (Player) sender;
        try {
            World world = Bukkit.getWorld(args.get(0));
            player.teleport(world.getSpawnLocation());
            Message.CMD_TELEPORT_SUCCESS.sendMessageWithPlaceHolder(sender, world.getName());
        } catch (NullPointerException exception) {
            Message.WORLD_NOT_FOUND.sendMessageWithPlaceHolder(sender, args.get(0));
        } catch (ArrayIndexOutOfBoundsException exception) {
            Message.NO_WORLD_TARGET.sendMessage(sender);
        }
    }
}
