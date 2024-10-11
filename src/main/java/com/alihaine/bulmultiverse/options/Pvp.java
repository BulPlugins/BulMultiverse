package com.alihaine.bulmultiverse.options;

import com.alihaine.bulmultiverse.world.WorldData;
import com.alihaine.bulmultiverse.world.WorldOption;
import org.bukkit.World;

public class Pvp extends WorldOption {
    public Pvp() {
        super("pvp", "-p", "-p <true or false>", "Enable pvp", true);
    }

    @Override
    public void optionExecutor(String value, WorldData worldData) {
        boolean parsedValue = Boolean.parseBoolean(value);
        worldData.getWorld().setPVP(parsedValue);
        worldData.addOption(this, parsedValue);
    }

    @Override
    public Object getDefaultValue(World world) {
        return world.getPVP();
    }
}
