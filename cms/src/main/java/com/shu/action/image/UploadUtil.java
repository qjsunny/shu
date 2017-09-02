package com.shu.action.image;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by james on 2017/5/8.
 */
public class UploadUtil {

    public static final List<String> ALLOW_TYPES = Arrays.asList(
            "image/jpg", "image/jpeg", "image/png", "image/gif"
    );

    /**
     * 文件重命名
     * @param fileName
     * @return
     */
    public static String rename(String fileName) {
        int i = fileName.lastIndexOf(".");
        String str = fileName.substring(i);
        return new Date().getTime() + "" + new Random().nextInt(99999999) + str;
    }

    /**
     * 校验文件类型是否是被允许的
     * @param postfix
     * @return
     */
    public static boolean allowUpload(String postfix) {
        return ALLOW_TYPES.contains(postfix);
    }

    /**
     * 删除文件、文件夹
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] ff = file.listFiles();
            for (File f : ff) {
                deleteFile(f.getPath());
            }
        }
        file.delete();
    }
}
