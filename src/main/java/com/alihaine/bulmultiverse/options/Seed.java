package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.WorldCreator;

public class Seed extends WorldOption {
    public Seed () {
        super("seed", "-s", false);
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) throws Exception {
        try {
            long seed = Long.parseLong(value);
            worldCreator.seed(seed);
        } catch (NumberFormatException exception) {
            throw new Exception("§cInvalid seed: " + value + " Need to contain numbers only.");
        }
    }
}
