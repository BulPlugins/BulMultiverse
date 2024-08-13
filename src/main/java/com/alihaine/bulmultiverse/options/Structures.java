package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.WorldCreator;

public class Structures extends WorldOption {
    public Structures() {
        this.needWorld = false;
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        worldCreator.generateStructures(Boolean.parseBoolean(value));
    }
}