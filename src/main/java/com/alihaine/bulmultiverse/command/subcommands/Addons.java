package com.alihaine.bulmultiverse.command.subcommands;

import com.alihaine.bulmultiverse.BulMultiverse;
import com.alihaine.bulmultiverse.addon.BulMultiverseAddon;
import com.alihaine.bulmultiverse.command.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Addons implements SubCommand {
    @Override
    public void executor(CommandSender sender, List<String> args) {
        final List<BulMultiverseAddon> addonList = BulMultiverse.getBulMultiverse().getAddonManager().getAddonsList();

        for (BulMultiverseAddon addon : addonList) {
            sender.sendMessage("Addon name: " + addon.getName());
            if (!args.isEmpty())
                continue;
            sender.sendMessage("Authors : " + addon.getAuthors());
            sender.sendMessage("Download links: " + addon.getDownloadLinks());
            sender.sendMessage("To get help and support about this addon: " + addon.getSupportLink());
        }
    }

    @Override
    public String getUsage() {
        return "/bmv addons (addon_name)";
    }

    @Override
    public String getDescription() {
        return "Display addons list or infos about a specified addon";
    }
}
