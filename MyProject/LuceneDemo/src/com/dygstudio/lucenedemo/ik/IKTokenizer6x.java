package com.dygstudio.lucenedemo.ik;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;

/**
 * @Project: LuceneDemo
 * @Author: diyaguang
 * @CreateDate: 2019/1/3-15:05
 * @Description:
 */
public class IKTokenizer6x extends Tokenizer {
    private IKSegmenter _IKImplement;  //IK分词器实现
    private final CharTermAttribute termAtt;   //词元文本属性
    private final OffsetAttribute offsetAtt; //词元位移属性
    private final TypeAttribute typeAtt; //词元分类属性
    private int endPosition;  //记录最后一个词元的结束位置

    //Lucene 6.x Tokenizer 适配器类构造函数，实现最新的 Tokenizer接口
    public IKTokenizer6x(boolean useSmart){
        super();
        offsetAtt = addAttribute(OffsetAttribute.class);
        termAtt = addAttribute(CharTermAttribute.class);
        typeAtt = addAttribute(TypeAttribute.class);
        _IKImplement = new IKSegmenter(input,useSmart);
    }

    @Override
    public boolean incrementToken() throws IOException{
        clearAttributes(); //清除所有的词元属性
        Lexeme nextLexeme = _IKImplement.next();
        //将 Lexeme 转化成 Attributes
        if(nextLexeme!=null){
            termAtt.append(nextLexeme.getLexemeText());  //设置词元文本
            termAtt.setLength(nextLexeme.getLength()); //设置词元长度
            offsetAtt.setOffset(nextLexeme.getBeginPosition(),nextLexeme.getEndPosition()); //设置词元位移
            endPosition = nextLexeme.getEndPosition();  //记录分词的最后位置
            typeAtt.setType(nextLexeme.getLexemeText());  //记录词元分类
            return true;  //返回 true，告知还有下一个词元
        }
        return false;  //返回 false，告知词元输出完毕
    }

    @Override
    public void reset() throws IOException{
        super.reset();
        _IKImplement.reset(input);
    }

    @Override
    public final void end(){
        int finalOffset = correctOffset(this.endPosition);
        offsetAtt.setOffset(finalOffset,finalOffset);
    }
}
