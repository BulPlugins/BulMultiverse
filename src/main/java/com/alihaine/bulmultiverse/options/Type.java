package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class Type extends WorldOption {
    public Type() {
        super("type", "-t", "-t <type>", "Type such as flat, amplified..", false);
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) throws Exception {
        try {
            WorldType worldType = WorldType.valueOf(value.toUpperCase());
            worldCreator.type(worldType);
        } catch (IllegalArgumentException exception) {
            throw new Exception("§cThe type " + value + " don't exist");
        }
    }
}
