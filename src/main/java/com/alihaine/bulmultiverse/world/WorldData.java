package com.alihaine.bulmultiverse.world;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.file.Message;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WorldData {
    private final String worldName;
    private Map<WorldOption, Object> optionsAndValue = new HashMap<>();
    private final WorldOptionManager worldOptionManager = BulMultiverse.getBulMultiverse().getWorldOptionManager();
    private final WorldDataManager worldDataManager = BulMultiverse.getBulMultiverse().getWorldDataManager();

    public WorldData(String name, Map<WorldOption, Object> options) {
        this.worldName = name;
        this.optionsAndValue = options;
    }

    public WorldData(ConfigurationSection worldSection) {
        this.worldName = worldSection.getName();
        worldSection.getValues(false).forEach((key, value) -> {
            try {
                optionsAndValue.put(worldOptionManager.getOption(key), value);
            } catch (Exception exception) {
                Bukkit.getConsoleSender().sendMessage(exception.getMessage());
            }
        });
    }

    public void createWorld(CommandSender sender) {
        WorldCreator newWorldCreator = new WorldCreator(this.getWorldName());

        Map<WorldOption, Object> options = new HashMap<>(optionsAndValue);
        Map<WorldOption, Object> optionsError = new HashMap<>();

        Iterator<Map.Entry<WorldOption, Object>> iterator = options.entrySet().iterator();
        sender.sendMessage("§e[BULMultiverse] §aStart the creation and loading of the world: §2" + this.getWorldName());
        while (iterator.hasNext()) {
            Map.Entry<WorldOption, Object> entry = iterator.next();
            if (entry.getKey().isWorldRequired())
                continue;
            try {
                entry.getKey().optionExecutor(entry.getValue().toString(), newWorldCreator);
            } catch (Exception exception) {
                new Message("error_world_creator").sendMessage(sender);
                optionsError.put(entry.getKey(), entry.getValue());
            }
            iterator.remove();
        }

        newWorldCreator.createWorld();

        iterator = options.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<WorldOption, Object> entry = iterator.next();
            try {
                entry.getKey().optionExecutor(entry.getValue().toString(), this);
            } catch (Exception exception) {
                new Message("error_world_creator").sendMessage(sender);
                optionsError.put(entry.getKey(), entry.getValue());
            }
            iterator.remove();
        }

        setAllNotSetOptions();

        optionsError.forEach((key, value) -> {
            optionsAndValue.put(key, key.getDefaultValue(getWorld()));
        });

        worldDataManager.addNewWorldData(this);
        new Message("cmd_load_success").withPlaceHolder("name", worldName).sendMessage(sender);
    }

    private void setAllNotSetOptions() {
        worldOptionManager.getAvailableOptionsList().forEach((option) -> {
            if (!this.optionsAndValue.containsKey(option))
                this.optionsAndValue.put(option, option.getDefaultValue(this.getWorld()));
        });
    }

    public void addOption(WorldOption worldOption, Object value) {
        this.optionsAndValue.put(worldOption, value);
    }

    public Map<String, Object> dumpsWorldDataForSave() {
        Map<String, Object> dumpedOptions = new HashMap<>();

        this.optionsAndValue.forEach((key, value) -> {
            dumpedOptions.put(key.getName(), value);
        });

        return dumpedOptions;
    }

    public String getWorldName() {
        return this.worldName;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.getWorldName());
    }

    public Map<WorldOption, Object> getOptionsAndValue() { return this.optionsAndValue; }

    public Object getWorldDataOptionValue(WorldOption worldOption) {
        return this.optionsAndValue.get(worldOption);
    }

}
