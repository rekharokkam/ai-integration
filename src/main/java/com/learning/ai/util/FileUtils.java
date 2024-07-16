package com.learning.ai.util;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {
    public static void saveBinaryToFile (byte[] data, String fileName) {
        try {
            Path directory = Paths.get("src/main/resources");
            if (! Files.exists(directory)){
                Files.createDirectory(directory);
            }
            Path filePath = directory.resolve(fileName);

            Files.write(filePath, data, StandardOpenOption.CREATE_NEW);
            System.out.printf("saved %s to %s%n", fileName, directory.toAbsolutePath());
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Exception occurred while writing to the byte data to a File",
                    e);
        }
    }
}
