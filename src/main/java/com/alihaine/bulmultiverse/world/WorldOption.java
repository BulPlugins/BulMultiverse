package com.alihaine.bulmultiverse.world;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.ConfigFile;
import com.alihaine.bulmultiverse.message.MessageType;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public abstract class WorldOption {
    protected String name;
    protected String shortName;
    protected String usage;
    protected String description;
    protected boolean needWorld;
    WorldOptionManager worldOptionManager = BulMultiverse.getWorldOptionManager();

    public WorldOption(String name, String shortName, String usage, String description, boolean needWorld) {
        this.name = name;
        this.shortName = shortName;
        this.usage = usage;
        this.description = description;
        this.needWorld = needWorld;
        worldOptionManager.addNewOption(this);
    }

    public void optionExecutor(String value, WorldCreator worldCreator) throws Exception {
        throw new UnsupportedOperationException(ConfigFile.getMessageFromConfig(MessageType.ERROR_WORLD_CREATOR));
    }

    public void optionExecutor(String value, WorldData worldData) throws Exception {
        throw new UnsupportedOperationException(ConfigFile.getMessageFromConfig(MessageType.ERROR_WORLD_CREATOR));
    }

    public boolean isWorldRequired() {
        return this.needWorld;
    }

    public boolean matches(String option) {
        return this.name.equalsIgnoreCase(option) || this.shortName.equalsIgnoreCase(option);
    }

    public abstract Object getDefaultValue(World world);

    public String getName() {
        return this.name;
    }

    public String getUsage() {
        return this.usage;
    }

    public String getDescription() {
        return this.description;
    }
}
