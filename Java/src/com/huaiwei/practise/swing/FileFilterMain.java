package com.huaiwei.practise.swing;

import com.huaiwei.practise.ImageNameFileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileFilterMain {

    public void execute(String file, String dirName, JTextArea area) {
        Reader in = null;
        BufferedReader bufferedReader = null;
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File f = new File(file);
            in = new FileReader(f);
            bufferedReader = new BufferedReader(in);
            List<String> strList = new ArrayList<>();
            String s = null;
            int i = 1;
            area.append("\n");
            while ((s = bufferedReader.readLine()) != null) {
                if (i <= 9) {
                    System.out.println("第0" + i + "行：   " + s);
                    area.append("第0" + i + "行：   " + s + "\n");
                } else {
                    System.out.println("第" + i + "行：   " + s);
                    area.append("第" + i + "行：   " + s + "\n");
                }
                i++;
                strList.add(s + ".png");
                strList.add(s + ".jpg");
            }
            ImageNameFileFilter imageNameFileFilter = new ImageNameFileFilter(strList);
            File dir = new File(dirName);
            File[] files = dir.listFiles(imageNameFileFilter);
            assert files != null;
            byte[] buf = new byte[1024];

            //输出到桌面
            FileSystemView view = FileSystemView.getFileSystemView();
            File homeDirectory = view.getHomeDirectory();
            String path = homeDirectory.getPath();
            File desktop = new File(path + "/结果");
            if (!desktop.exists()) {
                boolean mkdir = desktop.mkdir();
                if (!mkdir) {
                    area.append("创建输出目录失败");
                }
            }
            for (File f1 : files) {
                inStream = new FileInputStream(f1);
                outStream = new FileOutputStream(desktop + "/" + f1.getName());
                area.append(desktop.getAbsolutePath() + "\n");
                int read = 0;
                while ((read = inStream.read(buf)) != -1) {
                    outStream.write(buf, 0, read);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            area.append(e.toString());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (in != null) {
                    in.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                area.append(e.toString());
            }
        }
    }
}
