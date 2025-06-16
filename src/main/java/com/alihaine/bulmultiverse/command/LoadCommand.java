package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

@CommandAlias("bmv|bulmv|bulmultiverse")
public class LoadCommand extends BaseBmvCommand {

    @Subcommand("load")
    public void onLoad(CommandSender sender, String targetWorld) {
        Bukkit.getConsoleSender().sendMessage(targetWorld);

        if (!BulMultiverse.getBulMultiverse().getWorldsFile().isWorldFolderExisting(targetWorld)) {
            new Message("world_not_found").withPlaceHolder("name", targetWorld).sendMessage(sender);
            return;
        }
        WorldData worldData = new WorldData(targetWorld, new HashMap<>());
        worldData.createWorld(sender);
        BulMultiverse.getBulMultiverse().getWorldsFile().saveWorldDataToFile(worldData);
    }
}
