package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.world.WorldOption;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.file.ConfigFile;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create implements SubCommand {
    private final WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();
    private final ConfigFile configFile = BulMultiverse.getConfigFileInstance();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        Map<WorldOption, String> convertToOptionString = new HashMap<>();
        if (args.isEmpty()) {
            sender.sendMessage("§cYour world must have a name. §e/bmv help");
            return;
        }

        String worldName = args.get(0);
        if (configFile.isDisableWorldName(worldName)) {
            sender.sendMessage("§cThis name is forbidden and can't be a world: " + worldName);
            return;
        }

        for (int i = 1; i < args.size() - 1; i++) {
            String flag = args.get(i);
            try {
                convertToOptionString.put(worldOptionManager.getOption(flag), args.get(i+1));
            } catch (Exception exception) {
                sender.sendMessage("§cFlag " + flag + " not found, check §e/bmv flag");
            }
        }

        worldOptionManager.createWorldWithMap(sender, worldName, convertToOptionString);
        BulMultiverse.getWorldsFileInstance().saveWorldsToFile();
    }
}
