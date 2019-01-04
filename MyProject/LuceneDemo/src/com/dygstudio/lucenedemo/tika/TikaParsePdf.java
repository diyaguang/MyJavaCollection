package com.dygstudio.lucenedemo.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Project: LuceneDemo
 * @Author: diyaguang
 * @CreateDate: 2019/1/4-14:17
 * @Description:
 */
public class TikaParsePdf {
    public static void test() throws IOException, SAXException, TikaException{
        String filepath = "files/个税专项附加扣除政策解读-填报指引.pdf";
        File pdfFile = new File(filepath);
        BodyContentHandler handler = new BodyContentHandler();  //创建内容处理器对象
        Metadata metadata = new Metadata();  //创建元数据对象
        FileInputStream inputStream = new FileInputStream(pdfFile);
        ParseContext parseContext = new ParseContext();  //创建内容解析器对象
        PDFParser parser = new PDFParser();    //创建PDF解析器对象
        parser.parse(inputStream,handler,metadata,parseContext);   //解析文档
        System.out.println("文件属性信息：");
        for(String name : metadata.names()){
            System.out.println(name+" : "+metadata.get(name));
        }
        System.out.println("pdf文件中的内容");
        System.out.println(handler.toString());
    }
}
