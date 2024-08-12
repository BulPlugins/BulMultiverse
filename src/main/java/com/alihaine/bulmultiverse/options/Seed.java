package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.WorldCreator;

public class Seed extends WorldOption {
    public Seed () {
        this.needWorld = false;
    }

    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        worldCreator.seed(Long.parseLong(value));
    }
}
