package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.load")
public class LoadCommand extends BaseCommand {
    @Subcommand("load")
    @Description("Load existing world")
    @Syntax("/bmv load [world_name]")
    public void onLoad(CommandSender sender, String targetWorld) {
        Bukkit.getConsoleSender().sendMessage(targetWorld);

        if (!BulMultiverse.getWorldsFile().isWorldFolderExisting(targetWorld)) {
            new Message("world_not_found").withPlaceHolder("name", targetWorld).sendMessage(sender);
            return;
        }
        WorldData worldData = new WorldData(targetWorld, new HashMap<>());
        worldData.createWorld(sender);
        BulMultiverse.getWorldsFile().saveWorldDataToFile(worldData);
    }
}
