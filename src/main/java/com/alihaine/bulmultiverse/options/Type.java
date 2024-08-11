package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class Type implements WorldOption {
    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        worldCreator.type(WorldType.valueOf(value.toUpperCase()));
    }
}
