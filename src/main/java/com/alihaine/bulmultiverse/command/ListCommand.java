package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.list")
public class ListCommand extends BaseBmvCommand {
    @Subcommand("l|list|lists")
    @Description("List all the loaded worlds")
    @Syntax("/bmv list")
    public void onList(CommandSender commandSender) {
        commandSender.sendMessage("§aLoaded worlds");
        worldDataManager.getWorldsData().forEach(worldData -> {
            commandSender.sendMessage(worldData.getWorldName());
        });
    }
}
