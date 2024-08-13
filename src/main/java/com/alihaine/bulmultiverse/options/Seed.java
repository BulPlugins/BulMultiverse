package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.WorldCreator;

public class Seed extends WorldOption {
    public Seed () {
        this.needWorld = false;
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