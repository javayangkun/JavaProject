package com.huaiwei.practise;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilterFile {
    public static void main(String[] args) throws Exception {
        /*File f = new File("E:/学号/学号.txt");
        Reader in = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(in);
        List<String> strList = new ArrayList<>();
        String s = null;
        int i = 1;
        while ((s = bufferedReader.readLine()) != null) {

            if (i <= 9) {
                System.out.println("第0" + i + "行：   " + s);
            } else {
                System.out.println("第" + i + "行：   " + s);
            }
            i++;
            strList.add(s+".png");
            strList.add(s+".jpg");
        }
        ImageNameFileFilter imageNameFileFilter = new ImageNameFileFilter(strList);
        File file = new File("E:/图片");
        File[] files = file.listFiles(imageNameFileFilter);
        assert files != null;
        for (File f1 : files) {
            System.out.println(f1.getAbsolutePath());
        }*/

    }
}
