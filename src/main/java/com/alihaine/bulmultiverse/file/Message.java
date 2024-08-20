package com.alihaine.bulmultiverse.file;

import org.bukkit.command.CommandSender;

public enum Message {
    NO_WORLD_TARGET,
    WORLD_NOT_FOUND,
    FLAG_NOT_FOUND,
    FORBIDDEN_WORLD_NAME,
    CMD_LOAD_SUCCESS,
    CMD_TELEPORT_SUCCESS,
    CMD_UNLOAD_SUCCESS,
    ERROR_SET_OPTION,
    ERROR_WORLD_CREATOR,
    ONLY_INGAME_COMMAND,
    NO_PERMISSION;

    public void sendMessage(CommandSender sender) {
        String message = ConfigFile.getMessageFromConfig(this);
        sender.sendMessage(message);
    }

    public void sendMessageWithPlaceHolder(CommandSender sender, String holderValue) {
        String message = ConfigFile.getMessageFromConfig(this);
        message = message.replace("%name%", holderValue);
        sender.sendMessage(message);

    }
}
