package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;

public class Load implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        if (args.isEmpty()) {
            new Message("no_world_target").sendMessage(sender);
            return;
        }

        String worldName = args.get(0);
        Bukkit.getConsoleSender().sendMessage(worldName);

        if (!BulMultiverse.getBulMultiverse().getWorldsFile().isWorldFolderExisting(worldName)) {
            new Message("world_not_found").withPlaceHolder("name", args.get(0)).sendMessage(sender);
            return;
        }
        WorldData worldData = new WorldData(worldName, new HashMap<>());
        worldData.createWorld(sender);
        BulMultiverse.getBulMultiverse().getWorldsFile().saveWorldDataToFile(worldData);
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
