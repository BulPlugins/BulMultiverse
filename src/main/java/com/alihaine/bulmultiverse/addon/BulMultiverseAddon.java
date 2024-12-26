package com.alihaine.bulmultiverse.addon;

import com.alihaine.bulmultiverse.BulMultiverse;

import java.io.*;

public abstract class BulMultiverseAddon {
    public abstract void onEnable();

    public abstract void onEnableAfterWorldsLoad();

    public abstract void onDisable();

    public File createCustomFile(String fileName, InputStream defaultValues) throws IOException {
        File newFile = new File(BulMultiverse.getBulMultiverseInstance().getDataFolder(), fileName);
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
}
