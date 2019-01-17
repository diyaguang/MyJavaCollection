package com.dygstudio.lucenedemo.search;

import com.dygstudio.lucenedemo.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author: diyaguang
 * @date: 2019/01/17 2:52 PM
 * @description: com.dygstudio.lucenedemo.search
 */
public class SearchTest {
    public static void test() throws ParseException, IOException{
        String field = "title";
        Path indexPath = Paths.get("indexdir");
        Directory dir = FSDirectory.open(indexPath);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new IKAnalyzer6x();
        QueryParser parser = new QueryParser(field,analyzer);
        parser.setDefaultOperator(QueryParser.Operator.AND);
        Query query = parser.parse("农村学生");
        System.out.println("Query: "+query.toString());

        TopDocs tds = searcher.search(query,10);   //返回前10条记录
        for(ScoreDoc sd : tds.scoreDocs){
            Document doc = searcher.doc(sd.doc);
            System.out.println("DocID: "+sd.doc);
            System.out.println("id: "+doc.get("id"));
            System.out.println("title: "+doc.get("title"));
            System.out.println("文档评分: "+sd.score);
        }
        dir.close();
        reader.close();
    }
}
