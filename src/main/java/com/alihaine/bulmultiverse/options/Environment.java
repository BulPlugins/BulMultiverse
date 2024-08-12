package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class Environment extends WorldOption {
    public Environment() {
        this.needWorld = false;
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        worldCreator.environment(World.Environment.valueOf(value.toUpperCase()));
    }
}
