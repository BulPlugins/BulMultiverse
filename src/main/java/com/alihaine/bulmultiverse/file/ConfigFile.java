package com.alihaine.bulmultiverse.file;

import com.alihaine.bulmultiverse.BulMultiverse;

public class ConfigFile {
    public static BulMultiverse bulMultiverse;

    public static boolean isDisableWorldName(String worldName) {
        return bulMultiverse.getConfig().getList("world_disable_name").contains(worldName);
    }

    public static String getMessageFromConfig(Message message) {
        String msg = bulMultiverse.getConfig().getString("messages." + message.name().toLowerCase());
        if (msg != null)
            msg = msg.replaceAll("&", "§");
        return msg;
    }
}
