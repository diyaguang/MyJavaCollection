package com.dygstudio.lucenedemo.search;

import com.dygstudio.lucenedemo.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
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

    public static void MultiFieldQueryParser() throws ParseException{
        String[] fields = {"title","content"};
        Analyzer analyzer = new IKAnalyzer6x(true);
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,analyzer);
        Query multiFieldQuery = parser.parse("日本");
    }

    public static void TermQuery(){
        Term term = new Term("title","美国");
        Query termQuery = new TermQuery(term);
    }

    public static void BooleanQuery(){
        Query query1 = new TermQuery(new Term("title","美国"));
        Query query2 = new TermQuery(new Term("content","日本"));
        BooleanClause bc1 = new BooleanClause(query1, BooleanClause.Occur.MUST);
        BooleanClause bc2 = new BooleanClause(query2, BooleanClause.Occur.MUST_NOT);
        BooleanQuery boolQuery = new BooleanQuery.Builder().add(bc1).add(bc2).build();

    }

    public static void RangeQuery(){
        Query rangeQuery = IntPoint.newRangeQuery("reply",500,1000);
    }

    public static void PrefixQuery(){
        Term term = new Term("title","学");
        Query prefixQuery = new PrefixQuery(term);

    }

    public static void PhraseQuery(){
        PhraseQuery.Builder builder = new PhraseQuery.Builder();
        builder.add(new Term("title","日本"),2);
        builder.add(new Term("title","美国"),3);
        PhraseQuery phraseQuery = builder.build();
    }

    public static void FuzzyQuery(){
        Term trem = new Term("title","Tramp");
        FuzzyQuery fuzzyQuery = new FuzzyQuery(trem);

    }

    public static void WildcardQuery(){
        WildcardQuery wildcardQuery = new WildcardQuery(new Term("title","学?"));
    }
}
