package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.command.CommandSender;

public class ListWorlds implements SubCommand {

    @Override
    public void executor(CommandSender sender, java.util.List<String> args) {
        sender.sendMessage("§aLoaded worlds");
        BulMultiverse.getBulMultiverse().getWorldDataManager().getWorldsData().forEach(worldData -> {
            sender.sendMessage(worldData.getWorldName());
        });
    }

    @Override
    public String getUsage() {
        return "/bmv list";
    }

    @Override
    public String getDescription() {
        return "List all the loaded worlds";
    }
}
