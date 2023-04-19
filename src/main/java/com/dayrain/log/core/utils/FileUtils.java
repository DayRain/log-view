package com.dayrain.log.core.utils;

import java.io.File;
import java.util.Arrays;

public class FileUtils {

    /**
     * 判断是否是文件夹
     */
    public static boolean isValidPath(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }

    /**
     * 是否是文件
     */
    public static boolean isValidFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    /**
     * 获取文件夹内的文件名
     */
    public static String[] listFileNames(String filePath) {
        if (isValidPath(filePath)) {
            return new File(filePath).list();
        }
        return new String[]{};
    }

    public static void main(String[] args) {
        File file = new File("E:\\java\\project\\log-view\\logs");
        System.out.println(Arrays.toString(file.list()));
        System.out.println(isValidPath("E:\\java\\project\\log-view\\logs"));
    }
}
