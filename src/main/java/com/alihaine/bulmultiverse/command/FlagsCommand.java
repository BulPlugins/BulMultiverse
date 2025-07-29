package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
@CommandPermission("bulmultiverse.flags")
public class FlagsCommand extends BaseCommand {
    @Subcommand("flag|flags")
    @Description("Display all the available flags")
    @Syntax("/bmv flags")
    public void onFlags(CommandSender sender) {
        BulMultiverse.getWorldOptionManager().getAvailableOptionsList().forEach((value) -> {
            new Message("flags_pattern").
                    withPlaceHolder("usage", value.getUsage()).
                    withPlaceHolder("description", value.getDescription()).
                    sendMessage(sender);
        });
    }
}
