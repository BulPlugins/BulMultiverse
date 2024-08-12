package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.WorldOption;
import com.alihaine.bulmultiverse.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Set implements SubCommand {
    private final WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        World world = Bukkit.getWorld(args.get(0));
        try {
            WorldOption worldOption = worldOptionManager.getOption(worldOptionManager.buildOptionString(args.get(1)));
            worldOption.optionExecutor(args.get(2), world);
            sender.sendMessage("You set the value " + args.get(1) + ": " + args.get(2) + " to the world " + world.getName());
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage("ERROR NOT FOUND");
        }
        BulMultiverse.getWorldsFileInstance().saveWorldsToFile();
    }
}
