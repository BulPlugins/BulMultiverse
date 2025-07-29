package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.list")
public class ListCommand extends BaseCommand {
    @Subcommand("l|list|lists")
    @Description("List all the loaded worlds")
    @Syntax("/bmv list")
    public void onList(CommandSender commandSender) {
        commandSender.sendMessage("§aLoaded worlds");
        Set<String> loadedWorlds = BulMultiverse.getWorldDataManager()
                .getWorldsData()
                .stream()
                .map(WorldData::getWorldName)
                .collect(Collectors.toSet());

        Bukkit.getWorlds().forEach(world -> {
            if (loadedWorlds.contains(world.getName()))
                commandSender.sendMessage("§e" + world.getName());
            else
                commandSender.sendMessage("§e" + world.getName() + " §o§cnot loaded with BulMultiverse");
        });
    }
}
