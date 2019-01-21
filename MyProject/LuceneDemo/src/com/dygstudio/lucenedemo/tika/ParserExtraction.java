package com.dygstudio.lucenedemo.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author: diyaguang
 * @date: 2019/01/21 4:00 PM
 * @description: com.dygstudio.lucenedemo.tika
 */
public class ParserExtraction {
    public static void test() throws IOException, SAXException, TikaException{
        File fileDir = new File("files");
        if(!fileDir.exists()){
            System.out.println("文件夹不存在，请检查！");
            System.exit(0);
        }
        File[] fileArr = fileDir.listFiles();
        BodyContentHandler handler = new BodyContentHandler();  //内容容器对象
        Metadata metadata = new Metadata();    //元数据容器对象
        FileInputStream inputStream = null;
        Parser parser = new AutoDetectParser();  //自动检测解析器
        ParseContext context = new ParseContext();

        for(File f : fileArr){
            inputStream = new FileInputStream(f);
            ((AutoDetectParser) parser).parse(inputStream,handler,metadata,context);
            System.out.println(f.getName()+":\n"+handler.toString());
        }
    }
}
