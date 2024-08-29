package com.alihaine.bulmultiverse.world;

import com.alihaine.bulmultiverse.options.*;

import java.util.*;

public class WorldOptionManager {
    private final List<WorldOption> availableOptions = new ArrayList<>();

    public void loadDefaultOption() {
        new Environment();
        new Seed();
        new Structures();
        new Type();
        new Difficulty();
        new Pvp();
    }

    public WorldOption getOption(String optionAsString) throws Exception {
        for (WorldOption option : availableOptions) {
            if (option.matches(optionAsString))
                return option;
        }
        throw new Exception("§cOption " + optionAsString + " not found or not supported");
    }

    public void addNewOption(WorldOption option) {
        this.availableOptions.add(option);
    }

    public List<WorldOption> getAvailableOptionsList() {
        return this.availableOptions;
    }
}
