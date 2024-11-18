package io.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;


public class ReadTextFileV1 {
    public static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "abc\n가나다";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        Files.writeString(path, writeString, UTF_8);

        System.out.println("== Read String ==");
//        List<String> list = Files.readAllLines(path, UTF_8);
//        for (String line : list) {
//            System.out.println("line = " + line);
//        }
        Stream<String> lines = Files.lines(path, UTF_8);
        lines.forEach(line -> {
            System.out.println("line = " + line);
        });
    }
}
