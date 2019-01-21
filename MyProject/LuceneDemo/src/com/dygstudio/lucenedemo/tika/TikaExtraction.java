package com.dygstudio.lucenedemo.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;

/**
 * @author: diyaguang
 * @date: 2019/01/21 3:54 PM
 * @description: com.dygstudio.lucenedemo.tika
 */
public class TikaExtraction {
    public static void test() throws IOException, TikaException {
        Tika tika = new Tika();
        File fileDir = new File("files");
        if(!fileDir.exists()){
            System.out.println("文件夹不存在，请检查");
            System.exit(0);
        }
        File[] fileArr = fileDir.listFiles();
        String filecontent;
        for(File f : fileArr){
            filecontent = tika.parseToString(f);
            System.out.println("Extracted Content: "+filecontent);
        }

    }
}
