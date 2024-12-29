package com.alihaine.bulmultiverse.world;

import com.alihaine.bulmultiverse.BulMultiverse;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public abstract class WorldOption {
    protected String name;
    protected String shortName;
    protected String usage;
    protected String description;
    protected boolean needWorld;
    WorldOptionManager worldOptionManager = BulMultiverse.getBulMultiverse().getWorldOptionManager();

    public WorldOption(String name, String shortName, String usage, String description, boolean needWorld) {
        this.name = name;
        this.shortName = shortName;
        this.usage = usage;
        this.description = description;
        this.needWorld = needWorld;
        worldOptionManager.addNewOption(this);
    }

    public void optionExecutor(String value, WorldCreator worldCreator) throws Exception {
        throw new UnsupportedOperationException("Impossible to set the value " + value + " with worldcreator");
    }

    public void optionExecutor(String value, WorldData worldData) throws Exception {
        throw new UnsupportedOperationException("Impossible to set the value " + value + " with worldData");
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
