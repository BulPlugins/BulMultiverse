package com.alihaine.bulmultiverse.world;

import java.util.ArrayList;
import java.util.List;

public class WorldDataManager {
    List<WorldData> worldsData = new ArrayList<>();

    public void addNewWorldData(WorldData worldData) {
        worldsData.add(worldData);
    }

    public WorldData getWorldDataFromWorldName(String worldName) {
        for (WorldData worldData : this.worldsData) {
            if (worldData.getWorldName().equals(worldName))
                return worldData;
        }
        return null;
    }
}
