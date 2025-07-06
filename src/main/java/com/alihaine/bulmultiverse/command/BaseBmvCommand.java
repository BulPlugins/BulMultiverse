package com.alihaine.bulmultiverse.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.addon.AddonManager;
import com.alihaine.bulmultiverse.file.WorldsFile;
import com.alihaine.bulmultiverse.world.WorldDataManager;
import com.alihaine.bulmultiverse.world.WorldOptionManager;
import org.bukkit.command.CommandSender;

@CommandAlias(BaseBmvCommand.commandRootAlias)
public class BaseBmvCommand extends BaseCommand {
    /*
     * Commands may need to use these instances frequently
     * To improve readability and make access easier, they are defined here.
     */
    protected final WorldDataManager worldDataManager = BulMultiverse.getBulMultiverse().getWorldDataManager();
    protected final WorldsFile worldsFile = BulMultiverse.getBulMultiverse().getWorldsFile();
    protected final WorldOptionManager worldOptionManager = BulMultiverse.getBulMultiverse().getWorldOptionManager();
    protected final AddonManager addonManager = BulMultiverse.getBulMultiverse().getAddonManager();

    public static final String commandRootAlias = "bmv|bulmv|bulmultiverse";

    @Default
    public void onDefault(CommandSender sender) {
        sender.sendMessage("§eBulMultiverse §8| §6Version: " + BulMultiverse.getBulMultiverse().getDescription().getVersion());
        sender.sendMessage("§dDiscord support §8| §5https://discord.com/invite/wxnTV68dX2");
        sender.sendMessage("§a/bmv help §8| §2Display all commands");
    }
}
