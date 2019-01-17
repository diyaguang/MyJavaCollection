package com.dygstudio.lucenedemo.index;

import com.dygstudio.lucenedemo.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author: diyaguang
 * @date: 2019/01/17 2:22 PM
 * @description: com.dygstudio.lucenedemo.index
 */
public class DeleteIndex {
    public static void test(){
        deleteDoc("title","美国");
    }

    public static void deleteDoc(String field,String key){
        Analyzer analyzer = new IKAnalyzer6x();
        IndexWriterConfig icw = new IndexWriterConfig(analyzer);
        Path indexPath = Paths.get("indexdir");
        Directory directory;
        try{
            directory = FSDirectory.open(indexPath);
            IndexWriter indexWriter = new IndexWriter(directory,icw);
            indexWriter.deleteDocuments(new Term(field,key));
            indexWriter.commit();
            indexWriter.close();
            System.out.println("删除完成！");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
