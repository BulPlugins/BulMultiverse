package com.alihaine.bulmultiverse;

import java.io.*;
import java.nio.file.Files;

public abstract class BulMultiverseAddon {
    public abstract void onEnable();

    public abstract void onDisable();

    public File createCustomFile(String fileName, InputStream defaultValues) throws IOException {
        File newFile = new File(BulMultiverse.getBulMultiverseInstance().getDataFolder(), fileName);

        if (!newFile.exists()) {
            File parentFile = newFile.getParentFile();
            if (parentFile.mkdirs()) {
                if (newFile.createNewFile()) {
                    copyDefaultResource(fileName, newFile, defaultValues);
                }
            }
        }
        return newFile;
    }

    private void copyDefaultResource(String fileName, File file, InputStream defaultValues) throws IOException  {
        try (OutputStream out = Files.newOutputStream(file.toPath())) {

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
