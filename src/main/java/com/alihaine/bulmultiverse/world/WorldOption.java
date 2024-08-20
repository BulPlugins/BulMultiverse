package com.alihaine.bulmultiverse.world;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.ConfigFile;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public abstract class WorldOption {
    protected String name;
    protected String shortName;
    protected boolean needWorld;
    WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    public WorldOption(String name, String shortName, boolean needWorld) {
        this.name = name;
        this.shortName = shortName;
        this.needWorld = needWorld;
        worldOptionManager.addNewOption(this);
    }

    public void optionExecutor(String value, World world) throws Exception {
        throw new UnsupportedOperationException(ConfigFile.getMessageFromConfig(Message.ERROR_SET_OPTION));
    }

    public void optionExecutor(String value, WorldCreator worldCreator) throws Exception {
        throw new UnsupportedOperationException(ConfigFile.getMessageFromConfig(Message.ERROR_WORLD_CREATOR));
    }

    public boolean isWorldRequired() {
        return this.needWorld;
    }

    public boolean matches(String option) {
        return this.name.equalsIgnoreCase(option) || this.shortName.equalsIgnoreCase(option);
    }
}
