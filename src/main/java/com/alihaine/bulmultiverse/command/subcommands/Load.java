package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Load implements SubCommand {
    WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (args.isEmpty()) {
            sender.sendMessage("§cYour world must have a name. §e/bmv help");
            return;
        }

        String worldName = args.get(0);

        if (!worldOptionManager.isWorldFolderExisting(worldName)) {
            sender.sendMessage("§cNo world folder with name " + worldName + " found");
            return;
        }
        new WorldCreator(worldName).createWorld();
        sender.sendMessage("§aWorld " + worldName + " loaded");
    }
}
