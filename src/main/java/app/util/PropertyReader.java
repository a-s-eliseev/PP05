package app.util;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {

    public static String getProperty(String nameParam, String fileName) {
        Properties properties = new Properties();
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(fileName)).getPath();
        try {
            properties.load(new FileInputStream(rootPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(nameParam);
    }
}
