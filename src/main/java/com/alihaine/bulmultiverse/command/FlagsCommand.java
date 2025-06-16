package com.alihaine.bulmultiverse.command;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.command.CommandSender;

@CommandAlias("bmv|bulmv|bulmultiverse")
public class FlagsCommand extends BaseBmvCommand {
    @Subcommand("flag|flags")
    public void onFlags(CommandSender sender) {
        worldOptionManager.getAvailableOptionsList().forEach((value) -> {
            new Message("flags_pattern").
                    withPlaceHolder("usage", value.getUsage()).
                    withPlaceHolder("description", value.getDescription()).
                    sendMessage(sender);
        });
    }
}
