package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.message.Message;
import com.alihaine.bulmultiverse.message.MessageType;
import com.alihaine.bulmultiverse.file.WorldsFile;
import com.alihaine.bulmultiverse.message.PlaceHolder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Unload implements SubCommand {
    private final WorldsFile worldsFile = BulMultiverse.getWorldsFileInstance();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (args.isEmpty()) {
            new Message(MessageType.NO_WORLD_TARGET).sendMessage(sender);
            return;
        }

        World world = Bukkit.getWorld(args.get(0));
        if (world == null) {
            new Message(MessageType.WORLD_NOT_FOUND).withPlaceHolder(PlaceHolder.NAME, args.get(0)).sendMessage(sender);
            return;
        }

        if (!world.getPlayers().isEmpty()) {
            sender.sendMessage("§cYou can't unload a world who contain players");
            return;
        }

        worldsFile.removeWorldFromFile(world.getName());
        Bukkit.unloadWorld(world, true);
        new Message(MessageType.CMD_UNLOAD_SUCCESS).withPlaceHolder(PlaceHolder.NAME, args.get(0)).sendMessage(sender);
    }

    @Override
    public String getUsage() {
        return "/bmv unload <world_name>";
    }

    @Override
    public String getDescription() {
        return "UnLoad existing world";
    }
}
