package com.alihaine.bulmultiverse.file;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class WorldData {
    private final long seed;
    private final boolean PVP;
    private final String difficulty;
    private final String environment;
    private final String type;
    private final boolean structures;

    public WorldData(World world) {
        this.seed = world.getSeed();;
        this.PVP = world.getPVP();
        this.difficulty = world.getDifficulty().name();
        this.environment = world.getEnvironment().name();
        this.type = world.getWorldType().name();
        this.structures = world.canGenerateStructures();
    }

    public Map<String, Object> dumpsForSave() {
        Map<String, Object> data = new HashMap<>();
        data.put("seed", this.seed);
        data.put("pvp", this.PVP);
        data.put("difficulty", this.difficulty);
        data.put("environment", this.environment);
        data.put("type", this.type);
        data.put("structures", this.structures);

        return data;
    }
}
