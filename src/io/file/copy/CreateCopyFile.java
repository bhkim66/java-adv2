package io.file.copy;

import java.io.*;

public class CreateCopyFile {
    private static final int FILE_SIZE = 200 * 1024 * 1024; // 200MB

    public static void main(String[] args) throws IOException {
        String fileName = "temp/copy.dat";
        long startTime = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos, FILE_SIZE);

        bos.write(0);
        bos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("taken time : "+ (endTime - startTime) + " ms");
    }
}
