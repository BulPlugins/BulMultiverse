package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.Default;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.addon.AddonManager;
import com.alihaine.bulmultiverse.file.WorldsFile;
import com.alihaine.bulmultiverse.world.WorldDataManager;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import org.bukkit.command.CommandSender;

public class BaseBmvCommand extends BaseCommand {
    /*
    * Commands may need to use these instances frequently
    * To improve readability and make access easier, they are defined here.
     */
    protected final WorldDataManager worldDataManager = BulMultiverse.getBulMultiverse().getWorldDataManager();
    protected final WorldsFile worldsFile = BulMultiverse.getBulMultiverse().getWorldsFile();
    protected final WorldOptionManager worldOptionManager = BulMultiverse.getBulMultiverse().getWorldOptionManager();
    protected final AddonManager addonManager = BulMultiverse.getBulMultiverse().getAddonManager();

    @Default
    public void onDefault(CommandSender sender) {
        sender.sendMessage("§cMissing arguments. Use /<cmd> help for info.");
    }
}
