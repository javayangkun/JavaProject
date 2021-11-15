package com.huaiwei.practise;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class ImageNameFileFilter implements FilenameFilter {

    private final List<String> fileNameList;

    public ImageNameFileFilter(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }

    @Override
    public boolean accept(File dir, String name) {
        if (fileNameList == null || fileNameList.isEmpty()) {
            return false;
        }

        for (String str : fileNameList) {
            if (name.equals(str)) {
                return true;
            }
        }

        return false;
    }
}
