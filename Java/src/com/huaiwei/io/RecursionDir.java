package com.huaiwei.io;

import java.io.File;

public class RecursionDir {
    public static void main(String[] args) {
        File file = new File("D:\\360");
        recurDir(file);
    }

    private static void recurDir(File file) {
        File[] files = file.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                recurDir(f);
            }
        }

    }
}
