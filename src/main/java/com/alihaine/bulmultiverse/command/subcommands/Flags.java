package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.command.SubCommand;
import com.alihaine.bulmultiverse.message.Message;
import com.alihaine.bulmultiverse.message.MessageType;
import com.alihaine.bulmultiverse.message.PlaceHolder;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Flags implements SubCommand {
    private final WorldOptionManager worldOptionManagerInstance = BulMultiverse.getWorldOptionManager();

    @Override
    public void executor(CommandSender sender, List<String> args) {
        worldOptionManagerInstance.getAvailableOptionsList().forEach((value) -> {
            new Message(MessageType.FLAGS_PATTERN).
                    withPlaceHolder(PlaceHolder.USAGE, value.getUsage()).
                    withPlaceHolder(PlaceHolder.DESCRIPTION, value.getDescription()).
                    sendMessage(sender);
        });
    }

    @Override
    public String getUsage() {
        return "/flags";
    }

    @Override
    public String getDescription() {
        return "Display all the available flags";
    }
}
