package org.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Collections;

public class Logger {

    private static final String FILE_PATH = "OutputFolder/error.output.txt";

    public static void log(String message) {
        try {
            message += LocalDateTime.now() + " " + message;
            Path path = Paths.get(FILE_PATH);
            Files.write(path, Collections.singleton(message), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error in accessing log file");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Unknown error in processing to write log file");
            System.exit(0);
        }
    }
}
