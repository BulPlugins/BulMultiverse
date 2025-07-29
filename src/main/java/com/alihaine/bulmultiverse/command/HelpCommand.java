package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.HelpEntry;
import co.aikar.commands.annotation.*;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
public class HelpCommand extends BaseCommand {

    @Subcommand("help")
    @Description("Display the help menu")
    @Syntax("/bmv help")
    public void onHelp(CommandSender sender) {
        CommandHelp help = getCurrentCommandManager().generateCommandHelp("bmv");
        sender.sendMessage("§e§lBulMultiverse Commands:");
        for (HelpEntry entry : help.getHelpEntries()) {
            if (entry.getParameterSyntax().isEmpty() || entry.getDescription().isEmpty())
                continue;
            new Message("help_pattern").
                    withPlaceHolder("usage", entry.getParameterSyntax()).
                    withPlaceHolder("description", entry.getDescription()).
                    sendMessage(sender);
        }
    }
}
