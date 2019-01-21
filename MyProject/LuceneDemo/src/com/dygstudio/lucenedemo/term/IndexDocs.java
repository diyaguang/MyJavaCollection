package com.dygstudio.lucenedemo.term;

import com.dygstudio.lucenedemo.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author: diyaguang
 * @date: 2019/01/21 2:37 PM
 * @description: com.dygstudio.lucenedemo.term
 */
public class IndexDocs {
    public static void test() throws IOException{
        File newsFile = new File("testfile/lucene.txt");
        String text1 = textToString(newsFile);
        Analyzer smcAnalyzer = new IKAnalyzer6x(true);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(smcAnalyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        Directory directory = null;
        IndexWriter indexWriter = null;
        directory = FSDirectory.open(Paths.get("indexdir"));
        indexWriter = new IndexWriter(directory,indexWriterConfig);
        FieldType type = new FieldType();  //新建 FieldType字段，用于指定字段索引时的信息
        type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);  //索引时保存文档，词项频率，位置信息，偏移信息
        type.setStored(true);   //原始字符串全部被保存在索引中
        type.setStoreTermVectors(true);   //存储词响亮
        type.setTokenized(true);   //词条化
        Document doc1 = new Document();
        Field field1 = new Field("content",text1,type);
        doc1.add(field1);
        indexWriter.addDocument(doc1);
        indexWriter.close();
        directory.close();
    }

    public static String textToString(File file){
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = null;
            while((str = br.readLine()) != null){
                result.append(System.lineSeparator()+str);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
