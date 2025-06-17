package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.load")
public class LoadCommand extends BaseBmvCommand {
    @Subcommand("load")
    @Description("Load existing world")
    @Syntax("/bmv load [world_name]")
    public void onLoad(CommandSender sender, String targetWorld) {
        Bukkit.getConsoleSender().sendMessage(targetWorld);

        if (!worldsFile.isWorldFolderExisting(targetWorld)) {
            new Message("world_not_found").withPlaceHolder("name", targetWorld).sendMessage(sender);
            return;
        }
        WorldData worldData = new WorldData(targetWorld, new HashMap<>());
        worldData.createWorld(sender);
        worldsFile.saveWorldDataToFile(worldData);
    }
}
