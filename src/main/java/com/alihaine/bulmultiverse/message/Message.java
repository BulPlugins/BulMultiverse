package com.alihaine.bulmultiverse.message;

import com.alihaine.bulmultiverse.file.ConfigFile;
import org.bukkit.command.CommandSender;

public class Message {
    String message;

    public Message(MessageType messageType) {
        this.message = ConfigFile.getMessageFromConfig(messageType);
    }

    public void sendMessage(CommandSender sender) {
        sender.sendMessage(this.message);
    }

    public Message withPlaceHolder(PlaceHolder placeHolder, String value) {
        this.message = this.message.replace("%" + placeHolder.name().toLowerCase() + "%", value);
        return this;
    }
}
