package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.File;
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
        this.displayWorlds(commandSender, loadedWorlds);
    }

    private void displayWorlds(CommandSender commandSender, Set<String> loadedWorlds) {
        File worldContainer = Bukkit.getWorldContainer();
        File[] worldFolders = worldContainer.listFiles();

        if (worldFolders == null)
            return;
        for (File worldFolder : worldFolders) {
            //World will have at least one of these files.
            //The exact files depend on the server version and world type, and may differ.
            File levelDat = new File(worldFolder, "level.dat");
            File uidDat = new File(worldFolder, "uid.dat");
            File regionFolder = new File(worldFolder, "region");
            if (!levelDat.exists() && !regionFolder.exists() && !uidDat.exists())
                continue;
            if (loadedWorlds.contains(worldFolder.getName()))
                commandSender.sendMessage("§e" + worldFolder.getName());
            else if (Bukkit.getWorld(worldFolder.getName()) != null)
                commandSender.sendMessage("§e" + worldFolder.getName() + " §o§cnot loaded with BulMultiverse");
            else
                commandSender.sendMessage("§e" + worldFolder.getName() + " §o§dnot loaded at all");
        }
    }
}
