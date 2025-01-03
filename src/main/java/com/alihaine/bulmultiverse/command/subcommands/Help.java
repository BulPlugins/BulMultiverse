package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.BMV;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Help implements SubCommand {
    private final BMV bmvInstance = BulMultiverse.getBulMultiverse().getBMV();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        bmvInstance.getSubCommandsHashMap().forEach((key, value) -> {
            new Message("help_pattern").
                    withPlaceHolder("usage", value.getUsage()).
                    withPlaceHolder("description", value.getDescription()).
                    sendMessage(sender);
        });
    }

    @Override
    public String getUsage() {
        return "/bmv help";
    }

    @Override
    public String getDescription() {
        return "Display the help menu";
    }
}
