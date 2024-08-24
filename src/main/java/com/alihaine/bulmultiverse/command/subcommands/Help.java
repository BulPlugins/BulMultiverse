package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.message.Message;
import com.alihaine.bulmultiverse.message.MessageType;
import com.alihaine.bulmultiverse.message.PlaceHolder;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Help implements SubCommand {
    private final BMV bmvInstance = BulMultiverse.getBMVInstance();

    @Override
    public void executor(CommandSender sender, List<String> args) {

        bmvInstance.getSubCommandsHashMap().forEach((key, value) -> {
            new Message(MessageType.HELP_PATTERN).
                    withPlaceHolder(PlaceHolder.USAGE, value.getUsage()).
                    withPlaceHolder(PlaceHolder.DESCRIPTION, value.getDescription()).
                    sendMessage(sender);
        });
    }

    @Override
    public String getUsage() {
        return "/help";
    }

    @Override
    public String getDescription() {
        return "Display the help menu";
    }
}
