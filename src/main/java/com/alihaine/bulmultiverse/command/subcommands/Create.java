package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
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

    @Override
    public void executor(CommandSender sender, List<String> args) {
        Map<WorldOption, String> convertToOptionString = new HashMap<>();
        if (args.isEmpty()) {
            Message.NO_WORLD_TARGET.sendMessage(sender);
            return;
        }

        String worldName = args.get(0);
        if (ConfigFile.isDisableWorldName(worldName)) {
            Message.FORBIDDEN_WORLD_NAME.sendMessage(sender);
            return;
        }

        for (int i = 1; i < args.size() - 1; i++) {
            String flag = args.get(i);
            try {
                convertToOptionString.put(worldOptionManager.getOption(flag), args.get(i+1));
            } catch (Exception exception) {
                Message.FLAG_NOT_FOUND.sendMessageWithPlaceHolder(sender, flag);
            }
        }

        worldOptionManager.createWorldWithMap(sender, worldName, convertToOptionString);
        BulMultiverse.getWorldsFileInstance().saveWorldsToFile();
    }
}
