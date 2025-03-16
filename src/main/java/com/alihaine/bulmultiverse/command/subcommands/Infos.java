package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Infos implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        String worldName;
        if (args.isEmpty()) {
            if (!(sender instanceof Player)) {
                new Message("no_world_target").sendMessage(sender);
                return;
            }
            worldName = ((Player) sender).getWorld().getName();
        }
        else
            worldName = args.get(0);

        WorldData worldData = BulMultiverse.getBulMultiverse().getWorldDataManager().getWorldDataFromWorldName(worldName);
        if (worldData == null) {
            new Message("world_not_found").withPlaceHolder("name", worldName).sendMessage(sender);
            return;
        }

        sender.sendMessage("§a" + worldName + ":");
        worldData.getOptionsAndValue().forEach((key, value) -> {
            new Message("infos_pattern").
                    withPlaceHolder("name", key.getName()).
                    withPlaceHolder("value", value.toString()).
                    sendMessage(sender);
        });
    }

    @Override
    public String getUsage() {
        return "/bmv infos (world_name)";
    }

    @Override
    public String getDescription() {
        return "Display the help menu";
    }
}
