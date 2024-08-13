package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class Environment extends WorldOption {
    public Environment() {
        this.needWorld = false;
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) throws Exception {
        try {
            World.Environment environment = World.Environment.valueOf(value.toUpperCase());
            worldCreator.environment(environment);
        } catch (IllegalArgumentException exception) {
            throw new Exception("The environment " + value + " don't exist");
        }
    }
}