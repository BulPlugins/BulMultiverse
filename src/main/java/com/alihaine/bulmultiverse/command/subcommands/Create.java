package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.WorldOption;
import com.alihaine.bulmultiverse.WorldOptionManager;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Create implements SubCommand {
    private final WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        Map<WorldOption, String> convertToOptionString = new HashMap<>();

        for (int i = 1; i < args.size() - 1; i++) {
            try {
                String optionAsStr = worldOptionManager.buildOptionString(args.get(i));
                convertToOptionString.put(worldOptionManager.getOption(optionAsStr), args.get(i+1));
            } catch (Exception exception) {
                Bukkit.getConsoleSender().sendMessage("ERROR NOT FOUND");
            }
        }

        worldOptionManager.createWorldWithMap(args.get(0), convertToOptionString);
        BulMultiverse.getWorldsFileInstance().saveWorldsToFile();
    }
}
