package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class Type extends WorldOption {
    public Type() {
        this.needWorld = false;
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        worldCreator.type(WorldType.valueOf(value.toUpperCase()));
    }
}
