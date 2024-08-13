package com.alihaine.bulmultiverse.file;

import com.alihaine.bulmultiverse.BulMultiverse;

public class ConfigFile {
    BulMultiverse bulMultiverse = BulMultiverse.getBulMultiverseInstance();

    public boolean isDisableWorldName(String worldName) {
        return bulMultiverse.getConfig().getList("world_disable_name").contains(worldName);
    }
}
