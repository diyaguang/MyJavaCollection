package com.dygstudio.lucenedemo.ik;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * @Project: LuceneDemo
 * @Author: diyaguang
 * @CreateDate: 2019/1/3-15:33
 * @Description:
 */
public class IKAnalyzer6x extends Analyzer {
    private boolean useSmart;
    public boolean useSmart(){
        return useSmart;
    }
    public void setUseSmart(boolean useSmart){
        this.useSmart = useSmart;
    }
    public IKAnalyzer6x(){
        this(false);    //IK分词器，Lucene Analyzer接口实现类，默认细粒度切分算法
    }

    public IKAnalyzer6x(boolean useSmart){
        super();
        this.useSmart = useSmart;
    }

    @Override
    protected org.apache.lucene.analysis.Analyzer.TokenStreamComponents createComponents(String fieldName){
        //重写最新版本的 createComponents，重载 Analyzer接口，构造分词组件
        Tokenizer _IKTokenizer = new IKTokenizer6x(this.useSmart());
        return new org.apache.lucene.analysis.Analyzer.TokenStreamComponents(_IKTokenizer);
    }
}
