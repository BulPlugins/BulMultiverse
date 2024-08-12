package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.World;

public class Difficulty extends WorldOption {
    public Difficulty() {
        this.needWorld = true;
    }

    @Override
    public void optionExecutor(String value, World world) {
        world.setDifficulty(org.bukkit.Difficulty.valueOf(value.toUpperCase()));
    }
}
