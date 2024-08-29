package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class Structures extends WorldOption {
    public Structures() {
        super("structures", "-b", "-b <true or false>", "Enable default build such as village", false);
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        worldCreator.generateStructures(Boolean.parseBoolean(value));
    }

    @Override
    public Object getDefaultValue(World world) {
        return world.canGenerateStructures();
    }
}
