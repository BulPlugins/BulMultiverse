package com.alihaine.bulmultiverse.file;

import com.alihaine.bulmultiverse.BulMultiverse;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Message {
    String message;

    public Message(String messagePath) {
        this.message = BulMultiverse.getBulMultiverseInstance().getConfig().getString("messages." + messagePath);
    }

    public Message(String messagePath, FileConfiguration customConfigFile) {
        this.message = customConfigFile.getString("messages." + messagePath);
    }

    public void sendMessage(CommandSender sender) {
        sender.sendMessage(this.message.replaceAll("&", "§"));
    }

    public Message withPlaceHolder(String placeHolder, String value) {
        this.message = this.message.replace("%" + placeHolder + "%", value);
        return this;
    }
}
