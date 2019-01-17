package com.dygstudio.lucenedemo.index;

import com.dygstudio.lucenedemo.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author: diyaguang
 * @date: 2019/01/17 2:34 PM
 * @description: com.dygstudio.lucenedemo.index
 */
public class UpdateIndex {
    public static void test(){
        Analyzer analyzer = new IKAnalyzer6x();
        IndexWriterConfig icw = new IndexWriterConfig(analyzer);
        Path indexPath = Paths.get("indexdir");
        Directory directory;

        try{
            directory = FSDirectory.open(indexPath);
            IndexWriter indexWriter = new IndexWriter(directory,icw);
            Document doc = new Document();
            doc.add(new TextField("id","2", Field.Store.YES));
            doc.add(new TextField("title","北京大学开学迎来4380新生", Field.Store.YES));
            doc.add(new TextField("content","北京大学开学迎来4380名来自全国各地及数十个国家的本科新生，其中，农村学生共700余名，为近年来最多...", Field.Store.YES));
            indexWriter.updateDocument(new Term("title","北大"),doc);
            indexWriter.commit();
            indexWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
