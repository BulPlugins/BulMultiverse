package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.WorldOption;
import org.bukkit.World;

public class Pvp extends WorldOption {
    public Pvp() {
        super("pvp", "-p", true);
    }

    @Override
    public void optionExecutor(String value, World world) {
        world.setPVP(Boolean.parseBoolean(value));
    }
}
