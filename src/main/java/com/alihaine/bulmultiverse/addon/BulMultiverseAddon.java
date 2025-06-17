package com.alihaine.bulmultiverse.addon;

import com.alihaine.bulmultiverse.BulMultiverse;
import org.bukkit.event.Listener;

import java.io.*;
import java.util.List;

public abstract class BulMultiverseAddon {

    protected final String name;
    protected final List<String> authors;
    protected final List<String> downloadLinks;
    protected final String supportLink;

    public BulMultiverseAddon(String name, List<String> authors, List<String> downloadLinks, String supportLink) {
        this.name = name;
        this.authors = authors;
        this.downloadLinks = downloadLinks;
        this.supportLink = supportLink;
    }

    public BulMultiverseAddon(String name, List<String> authors, List<String> downloadLinks) {
        this.name = name;
        this.authors = authors;
        this.downloadLinks = downloadLinks;
        this.supportLink = "https://discord.gg/HQ6WnVTQJm";
    }

    public abstract void onEnable();

    public abstract void onEnableAfterWorldsLoad();

    public abstract void onDisable();

    public File createCustomFile(String fileName, InputStream defaultValues) {
        File newFile = new File(BulMultiverse.getBulMultiverse().getDataFolder(), fileName);
        if (!newFile.exists()) {
            newFile.getParentFile().mkdirs();
            try {
                newFile.createNewFile();
                copyDefaultResource(fileName, newFile, defaultValues);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return newFile;
    }

    public void registerEvents(Listener listener) {
        BulMultiverse.getBulMultiverse().getServer().getPluginManager().registerEvents(listener, BulMultiverse.getBulMultiverse());
    }

    private void copyDefaultResource(String fileName, File file, InputStream defaultValues) throws IOException  {
        try (OutputStream out = new FileOutputStream(file)) {

            if (defaultValues == null) {
                throw new IllegalArgumentException("Resource not found: " + fileName);
            }

            byte[] buffer = new byte[1024];
            int length;
            while ((length = defaultValues.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getDownloadLinks() {
        return downloadLinks;
    }

    public String getSupportLink() {
        return supportLink;
    }

}
