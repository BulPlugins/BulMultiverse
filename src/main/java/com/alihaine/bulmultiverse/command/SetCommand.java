package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import com.alihaine.bulmultiverse.world.WorldData;
import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.set")
public class SetCommand extends BaseCommand {
    @Subcommand("set")
    @Description("Set a flag to the target world")
    @Syntax("/bmv set [world_name] [flag] [value]")
    public void onSet(CommandSender sender, String targetWorld, String flagName, String flagValue) {
        WorldData worldData = BulMultiverse.getWorldDataManager().getWorldDataFromWorldName(targetWorld);
        if (worldData == null) {
            new Message("world_not_found").withPlaceHolder("name", targetWorld).sendMessage(sender);
            return;
        }

        try {
            WorldOption worldOption = BulMultiverse.getWorldOptionManager().getOption(flagName);
            worldOption.optionExecutor(flagValue, worldData);
            sender.sendMessage("§e[BULMultiverse] §aYou set the value §e" + flagName + ": " + flagValue + " §ato the world §e" + worldData.getWorldName());
        } catch (Exception exception) {
            new Message("error_world_creator").sendMessage(sender);
        }
        BulMultiverse.getWorldsFile().saveWorldDataToFile(worldData);

    }

}
