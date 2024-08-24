package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.World;

public class Pvp extends WorldOption {
    public Pvp() {
        super("pvp", "-p", "-p <true or false>", "Enable pvp", true);
    }

    @Override
    public void optionExecutor(String value, World world) {
        world.setPVP(Boolean.parseBoolean(value));
    }
}
