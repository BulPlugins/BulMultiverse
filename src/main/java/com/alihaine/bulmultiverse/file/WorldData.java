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
    private boolean animals;
    private boolean monsters;
    private String environment;
    private String type;
    private boolean keepSpawnInMemory;
    private long time;

    public WorldData(World world) {
        this.seed = world.getSeed();;
        this.PVP = world.getPVP();
        this.difficulty = world.getDifficulty().name();
        this.animals = world.getAllowAnimals();
        this.monsters = world.getAllowMonsters();
        this.environment = world.getEnvironment().name();
        this.type = world.getWorldType().name();
        this.keepSpawnInMemory = world.getKeepSpawnInMemory();
        this.time = world.getTime();
    }

    public Map<String, Object> dumpsForSave() {
        Map<String, Object> data = new HashMap<>();
        data.put("PVP", this.PVP);
        data.put("difficulty", this.difficulty);
        data.put("animals", this.animals);
        data.put("monsters", this.monsters);
        data.put("environment", this.environment);
        data.put("type", this.type);
        data.put("keepSpawnInMemory", this.keepSpawnInMemory);
        data.put("time", this.time);
        return data;
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

    public boolean isKeepSpawnInMemory() {
        return keepSpawnInMemory;
    }

    public long getTime() {
        return time;
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

    public void setKeepSpawnInMemory(boolean keepSpawnInMemory) {
        this.keepSpawnInMemory = keepSpawnInMemory;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
