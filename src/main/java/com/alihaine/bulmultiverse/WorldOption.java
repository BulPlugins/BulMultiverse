package com.alihaine.bulmultiverse;

import org.bukkit.World;
import org.bukkit.WorldCreator;

public abstract class WorldOption {
    protected boolean needWorld;

    public void optionExecutor(String value, World world) throws Exception {
        throw new UnsupportedOperationException("§cImpossible to set this option");
    }

    public void optionExecutor(String value, WorldCreator worldCreator) throws Exception {
        throw new UnsupportedOperationException("§cThis option does not support WorldCreator");
    }

    public boolean isWorldRequired() {
        return this.needWorld;
    }
}
