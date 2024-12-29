package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import com.alihaine.bulmultiverse.world.WorldOption;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Set implements SubCommand {
    private final WorldOptionManager worldOptionManager = BulMultiverse.getBulMultiverse().getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        WorldData worldData = BulMultiverse.getBulMultiverse().getWorldDataManager().getWorldDataFromWorldName(args.get(0));
        if (worldData == null) {
            new Message("world_not_found").withPlaceHolder("name", args.get(0)).sendMessage(sender);
            return;
        }
        try {
            WorldOption worldOption = worldOptionManager.getOption(args.get(1));
            worldOption.optionExecutor(args.get(2), worldData);
            sender.sendMessage("§e[BULMultiverse] §aYou set the value §e" + args.get(1) + ": " + args.get(2) + " §ato the world §e" + worldData.getWorldName());
        } catch (Exception exception) {
            new Message("error_world_creator").sendMessage(sender);
        }
        BulMultiverse.getBulMultiverse().getWorldsFile().saveWorldDataToFile(worldData);
    }

    @Override
    public String getUsage() {
        return "/bmv set <world_name> <option>";
    }

    @Override
    public String getDescription() {
        return "Set a option to the target world";
    }
}
