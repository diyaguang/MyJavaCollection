package com.dygstudio.lucenedemo.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * @Project: LuceneDemo
 * @Author: diyaguang
 * @CreateDate: 2019/1/3-11:40
 * @Description:
 */
public class StdAnalyzer {
    private static String strCh = "中华人民共和国简称中国，是一个有13亿人口的国家";
    private static String strEn = "Dogs can not achieve a place,eyes can reach;";

    public static void test(){
        try {
            System.out.println("StandardAnalyzer 对中文分词：");
            stdAnalyzer(strCh);
            System.out.println("StandardAnalyzer 对英文分词：");
            stdAnalyzer(strEn);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void test2(){
        try {
            Analyzer analyzer = null;
            analyzer = new StandardAnalyzer();   //标准分词
            System.out.println("标准分词:"+analyzer.getClass());
            printAnalyzer(analyzer);

            analyzer = new WhitespaceAnalyzer();   //空格分词
            System.out.println("空格分词:"+analyzer.getClass());
            printAnalyzer(analyzer);

            analyzer = new SimpleAnalyzer();   //简单分词
            System.out.println("简单分词:"+analyzer.getClass());
            printAnalyzer(analyzer);

            analyzer = new CJKAnalyzer();   //二分法分词
            System.out.println("二分法分词:"+analyzer.getClass());
            printAnalyzer(analyzer);

            analyzer = new KeywordAnalyzer();   //关键词分词
            System.out.println("关键词分词:"+analyzer.getClass());
            printAnalyzer(analyzer);

            analyzer = new StopAnalyzer();   //停用词分词
            System.out.println("停用词分词:"+analyzer.getClass());
            printAnalyzer(analyzer);

            analyzer = new SmartChineseAnalyzer();   //中文智能分词
            System.out.println("中文智能分词:"+analyzer.getClass());
            printAnalyzer(analyzer);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void stdAnalyzer(String str) throws IOException{
        Analyzer analyzer = null;
        analyzer = new StandardAnalyzer();
        StringReader reader = new StringReader(str);
        TokenStream toStream = analyzer.tokenStream(str,reader);
        toStream.reset();
        CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
        System.out.println("分词结果: ");
        while (toStream.incrementToken()){
            System.out.print(teAttribute.toString()+"|");
        }
        System.out.println("\n");
        analyzer.close();
    }

    public static void printAnalyzer(Analyzer analyzer) throws IOException{
        StringReader reader = new StringReader(strCh);
        TokenStream toStream = analyzer.tokenStream(strCh,reader);
        toStream.reset();  //清空流
        CharTermAttribute termAttribute = toStream.getAttribute(CharTermAttribute.class);
        while (toStream.incrementToken()){
            System.out.print(termAttribute.toString()+"|");
        }
        System.out.println("\n");
        analyzer.close();
    }
}
