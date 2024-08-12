package com.alihaine.bulmultiverse;

import org.bukkit.WorldCreator;

public abstract class WorldOption {
    protected boolean needWorld;

    public abstract void optionExecutor(String value, WorldCreator worldCreator);

    public boolean isWorldRequired() {
        return this.needWorld;
    }
}
