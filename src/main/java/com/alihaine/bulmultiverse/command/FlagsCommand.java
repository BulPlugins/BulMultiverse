package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.flags")
public class FlagsCommand extends BaseBmvCommand {
    @Subcommand("flag|flags")
    @Description("Display all the available flags")
    @Syntax("/bmv flags")
    public void onFlags(CommandSender sender) {
        worldOptionManager.getAvailableOptionsList().forEach((value) -> {
            new Message("flags_pattern").
                    withPlaceHolder("usage", value.getUsage()).
                    withPlaceHolder("description", value.getDescription()).
                    sendMessage(sender);
        });
    }
}
