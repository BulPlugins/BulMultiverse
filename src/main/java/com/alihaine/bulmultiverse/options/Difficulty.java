package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.world.WorldData;
import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.World;

public class Difficulty extends WorldOption {
    public Difficulty() {
        super("difficulty", "-d", "-d <difficulty>", "Difficulty such as easy, normal..", true);
    }

    @Override
    public void optionExecutor(String value, WorldData worldData) throws Exception {
        try {
            org.bukkit.Difficulty difficulty = org.bukkit.Difficulty.valueOf(value.toUpperCase());
            worldData.getWorld().setDifficulty(difficulty);
            worldData.addOption(this, difficulty.name());
        } catch (IllegalArgumentException exception) {
            throw new Exception("The difficulty " + value + " don't exist");
        }
    }

    @Override
    public Object getDefaultValue(World world) {
        return world.getDifficulty().name();
    }
}
