package com.jimingqiang.study.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by QDHL on 2018/9/16.
 *
 * @author mingqiang ji
 *
 *
 * 官方说明地址：http://ifeve.com/google-guava-newcollectiontypes/?spm=a2c4e.11153940.blogcont20551.13.60fc78bbZwx5ZV
 */
public class GuavaMultiset {


    /**
     * 用于记录字符串在数组中出现的次数
     */
    @Test
    public void testMultsetWordCount(){
        String strWorld="wer|dfd|dd|dfd|dda|de|dr";
        String[] words=strWorld.split("\\|");
        List<String> wordList=new ArrayList<String>();
        for (String word : words) {
            wordList.add(word);
        }
        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(wordList);

        for(String key:wordsMultiset.elementSet()){
            System.out.println(key+" count："+wordsMultiset.count(key));
        }

        //count(E) 给定元素在Multiset中的计数
        System.out.println("给定元素在Multiset中的计数:"+wordsMultiset.count("wer"));
        System.out.println("返回集合元素的总个数（包括重复的元素）before:"+wordsMultiset.size());

        //add(E, int)增加给定元素在Multiset中的计数
        wordsMultiset.add("wer",4);
        System.out.println("给定元素在Multiset中的计数:"+wordsMultiset.count("wer"));
        System.out.println("返回集合元素的总个数（包括重复的元素）:"+wordsMultiset.size());

        //remove(E, int)	减少给定元素在Multiset中的计数
        wordsMultiset.remove("wer",3);
        System.out.println("给定元素在Multiset中的计数:"+wordsMultiset.count("wer"));

        //setCount(E, int)	设置给定元素在Multiset中的计数，不可以为负数
        wordsMultiset.setCount("wer",6);
        System.out.println("给定元素在Multiset中的计数:"+wordsMultiset.count("wer"));

        System.out.println("集合元素的总个数"+wordsMultiset.size());

        System.out.println("对于不重复元素的个数"+wordsMultiset.elementSet().size());
    }


}
