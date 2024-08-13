package com.alihaine.bulmultiverse.file;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class WorldData {
    private long seed;
    private boolean PVP;
    private String difficulty;
    private String environment;
    private String type;
    private boolean structures;

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

    public long getSeed() {
        return seed;
    }

    public boolean isPVP() {
        return PVP;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getType() {
        return type;
    }

    public boolean getStructures() {
        return structures;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public void setPVP(boolean PVP) {
        this.PVP = PVP;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStructures(boolean structure) {
        this.structures = structure;
    }
}
