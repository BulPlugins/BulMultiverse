package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.alihaine.bulmultiverse.BulMultiverse;
import org.bukkit.command.CommandSender;

@CommandAlias("bmv|bulmv|bulmultiverse")
public class ListCommand extends BaseBmvCommand {

    @Subcommand("l|list|lists")
    public void onList(CommandSender commandSender) {
        commandSender.sendMessage("§aLoaded worlds");
        BulMultiverse.getBulMultiverse().getWorldDataManager().getWorldsData().forEach(worldData -> {
            commandSender.sendMessage(worldData.getWorldName());
        });
    }
}
