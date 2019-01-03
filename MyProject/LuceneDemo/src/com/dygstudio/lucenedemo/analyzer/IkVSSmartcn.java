package com.dygstudio.lucenedemo.analyzer;

import com.dygstudio.lucenedemo.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * @Project: LuceneDemo
 * @Author: diyaguang
 * @CreateDate: 2019/1/3-15:43
 * @Description:
 */
public class IkVSSmartcn {
    private static String str1 = "公路局正在治理解放大道路面积水问题";
    private static String str2 = "IKAnalyzer 是一个开源的，基于 java 语言开发的轻量级的中文分词工具包。";

    public static void test(){
        try {
            Analyzer analyzer = null;
            System.out.println("句子一:"+str1);
            System.out.println("SmartChineseAnalyzer 分词结果：");
            analyzer = new SmartChineseAnalyzer();
            printAnalyzer(analyzer,str1);
            System.out.println("IKAnalyzer 分词结果： ");
            analyzer = new IKAnalyzer6x(true);
            printAnalyzer(analyzer,str1);
            System.out.println("--------------------------------------------------");
            System.out.println("句子二:"+str2);
            System.out.println("SmartChineseAnalyzer 分词结果：");
            analyzer = new SmartChineseAnalyzer();
            printAnalyzer(analyzer,str2);
            System.out.println("IKAnalyzer 分词结果： ");
            analyzer = new IKAnalyzer6x(true);
            printAnalyzer(analyzer,str2);
            analyzer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printAnalyzer(Analyzer analyzer,String str) throws IOException{
        StringReader reader = new StringReader(str);
        TokenStream toStream = analyzer.tokenStream(str,reader);
        toStream.reset();   //清空流
        CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
        while(toStream.incrementToken()){
            System.out.print(teAttribute.toString()+" | ");
        }
        System.out.println();
    }
}
