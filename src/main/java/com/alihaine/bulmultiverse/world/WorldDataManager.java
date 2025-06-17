package com.alihaine.bulmultiverse.world;

import java.util.ArrayList;
import java.util.List;

public class WorldDataManager {
    List<WorldData> worldsData = new ArrayList<>();

    public void addNewWorldData(WorldData worldData) {
        worldsData.add(worldData);
    }

    public void removeWorldData(String worldName) {
        for (WorldData worldData : worldsData) {
            if (worldData.getWorldName().equalsIgnoreCase(worldName)) {
                worldsData.remove(worldData);
                break;
            }
        }
    }

    public WorldData getWorldDataFromWorldName(String worldName) {
        for (WorldData worldData : this.worldsData) {
            if (worldData.getWorldName().equals(worldName))
                return worldData;
        }
        return null;
    }

    public List<WorldData> getWorldsData() { return this.worldsData; }

    public boolean isWorldAlreadyCreated(String worldName) {
        for (WorldData worldData: this.worldsData) {
            if (worldData.getWorldName().equalsIgnoreCase(worldName))
                return true;
        }
        return false;
    }
}
