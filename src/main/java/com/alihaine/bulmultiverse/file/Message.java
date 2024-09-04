package com.alihaine.bulmultiverse.file;

import org.bukkit.command.CommandSender;

public class Message {
    String message;

    public Message(String messagePath) {
        this.message = ConfigFile.getMessageFromConfig(messagePath);
    }

    public void sendMessage(CommandSender sender) {
        sender.sendMessage(this.message);
    }

    public Message withPlaceHolder(String placeHolder, String value) {
        this.message = this.message.replace("%" + placeHolder + "%", value);
        return this;
    }
}
