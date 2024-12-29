package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import com.alihaine.bulmultiverse.world.WorldOption;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.file.ConfigFile;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create implements SubCommand {
    private final WorldOptionManager worldOptionManager = BulMultiverse.getBulMultiverse().getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        Map<WorldOption, Object> convertToOptionString = new HashMap<>();
        if (args.isEmpty()) {
            new Message("no_world_target").sendMessage(sender);
            return;
        }

        String worldName = args.get(0);
        if (ConfigFile.isDisableWorldName(worldName)) {
            new Message("forbidden_world_name").sendMessage(sender);
            return;
        }

        if (BulMultiverse.getBulMultiverse().getWorldDataManager().isWorldAlreadyCreated(worldName) || worldName.equalsIgnoreCase("overworld") ) {
            new Message("world_already_exist").sendMessage(sender);
            return;
        }

        for (int i = 1; i < args.size() - 1; i+=2) {
            String flag = args.get(i);
            try {
                convertToOptionString.put(worldOptionManager.getOption(flag), args.get(i+1));
            } catch (Exception exception) {
                new Message("flag_not_found").withPlaceHolder("name", flag).sendMessage(sender);
            }
        }

        WorldData worldData = new WorldData(worldName, convertToOptionString);
        worldData.createWorld(sender);
        BulMultiverse.getBulMultiverse().getWorldsFile().saveWorldDataToFile(worldData);
    }

    @Override
    public String getUsage() {
        return "/bmv create <world_name> (options)";
    }

    @Override
    public String getDescription() {
        return "Create a new world";
    }
}
