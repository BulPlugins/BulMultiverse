package com.alihaine.bulmultiverse;

import com.alihaine.bulmultiverse.command.BMV;
import org.bukkit.plugin.java.JavaPlugin;

public class BulMultiverse extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("bmv").setExecutor(new BMV());
        System.out.println("enable");
    }

    @Override
    public void onDisable() {
        System.out.println("disable");
    }
}
