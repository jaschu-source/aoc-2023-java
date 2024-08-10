package org.jaschu.christmas.fun.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class InputReader {

    public static Supplier<Stream<String>> readFileInputOld(String resourceName) {
        URL fileUrl = InputReader.class
                .getClassLoader()
                .getResource(resourceName);

        try {
            assert fileUrl != null;
            try (Stream<String> linesStream = Files.lines(Paths.get(fileUrl.toURI()))) {
                return () -> linesStream;
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readFileInputLines(String resourceName) throws IOException {
        return Resources.readLines(ClassLoader.getSystemResource(resourceName), Charsets.UTF_8);
    }
}
