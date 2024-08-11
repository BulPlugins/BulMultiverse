package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.WorldOption;
import com.alihaine.bulmultiverse.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Create implements SubCommand {
    private final WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        WorldCreator worldCreator = new WorldCreator(args.get(0));

        for (int i = 1; i < args.size() - 1; i++) {
            try {
                String buildOptionString = worldOptionManager.buildOptionString(args.get(i));
                WorldOption worldOption = worldOptionManager.getOption(buildOptionString);
                worldOption.optionExecutor(args.get(i+1), worldCreator);
            } catch (Exception exception) {
                Bukkit.getConsoleSender().sendMessage("ERROR NOT FOUND");
            }
        }
        World w = worldCreator.createWorld();
        BulMultiverse.getWorldsFileInstance().saveWorldsToFile();
    }
}
