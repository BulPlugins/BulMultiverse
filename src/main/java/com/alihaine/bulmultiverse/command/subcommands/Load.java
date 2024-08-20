package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
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
            Message.NO_WORLD_TARGET.sendMessage(sender);
            return;
        }

        String worldName = args.get(0);

        if (!worldOptionManager.isWorldFolderExisting(worldName)) {
            Message.WORLD_NOT_FOUND.sendMessageWithPlaceHolder(sender, args.get(0));
            return;
        }
        new WorldCreator(worldName).createWorld();
        Message.CMD_LOAD_SUCCESS.sendMessageWithPlaceHolder(sender, worldName);
    }
}
