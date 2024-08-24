package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.message.Message;
import com.alihaine.bulmultiverse.message.MessageType;
import com.alihaine.bulmultiverse.message.PlaceHolder;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Load implements SubCommand {
    WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (args.isEmpty()) {
            new Message(MessageType.NO_WORLD_TARGET).sendMessage(sender);
            return;
        }

        String worldName = args.get(0);

        if (!worldOptionManager.isWorldFolderExisting(worldName)) {
            new Message(MessageType.WORLD_NOT_FOUND).withPlaceHolder(PlaceHolder.NAME, args.get(0)).sendMessage(sender);
            return;
        }
        new WorldCreator(worldName).createWorld();
        new Message(MessageType.CMD_LOAD_SUCCESS).withPlaceHolder(PlaceHolder.NAME, worldName).sendMessage(sender);
    }

    @Override
    public String getUsage() {
        return "/bmv load <world_name>";
    }

    @Override
    public String getDescription() {
        return "Load existing world";
    }
}
