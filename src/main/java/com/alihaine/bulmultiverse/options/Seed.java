package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.WorldCreator;

public class Seed implements WorldOption {
    @Override
    public void optionExecutor(String value, WorldCreator worldCreator) {
        worldCreator.seed(Long.parseLong(value));
    }
}
