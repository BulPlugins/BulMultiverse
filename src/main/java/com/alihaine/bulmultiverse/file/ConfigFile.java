package com.alihaine.bulmultiverse.file;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.message.MessageType;

public class ConfigFile {
    public static BulMultiverse bulMultiverse;

    public static boolean isDisableWorldName(String worldName) {
        return bulMultiverse.getConfig().getList("world_disable_name").contains(worldName);
    }

    public static String getMessageFromConfig(MessageType message) {
        String msg = bulMultiverse.getConfig().getString("messages." + message.name().toLowerCase());
        if (msg != null)
            msg = msg.replaceAll("&", "§");
        return msg;
    }
}
