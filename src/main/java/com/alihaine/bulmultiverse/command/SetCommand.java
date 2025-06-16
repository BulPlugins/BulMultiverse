package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.command.CommandSender;

@CommandAlias("bmv|bulmv|bulmultiverse")
public class SetCommand extends BaseBmvCommand {
    @Subcommand("set")
    public void onSet(CommandSender sender, String targetWorld, String flagName, String flagValue) {
        WorldData worldData = worldDataManager.getWorldDataFromWorldName(targetWorld);
        if (worldData == null) {
            new Message("world_not_found").withPlaceHolder("name", targetWorld).sendMessage(sender);
            return;
        }

        try {
            WorldOption worldOption = worldOptionManager.getOption(flagName);
            worldOption.optionExecutor(flagValue, worldData);
            sender.sendMessage("§e[BULMultiverse] §aYou set the value §e" + flagName + ": " + flagValue + " §ato the world §e" + worldData.getWorldName());
        } catch (Exception exception) {
            new Message("error_world_creator").sendMessage(sender);
        }
        BulMultiverse.getBulMultiverse().getWorldsFile().saveWorldDataToFile(worldData);

    }

}
