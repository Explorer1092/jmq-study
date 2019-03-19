package com.jimingqiang.study.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jimingqiang
 * @Date: 2019/3/19 16:17
 * @Description:
 */
public class GuavaString {

    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, null);


        Integer[] integers = list.toArray(new Integer[list.size()]);


        String join = Joiner.on(",").skipNulls().join(list);

        String join1 = Joiner.on(",").skipNulls().join(integers);

        System.out.println(join);
        System.out.println(join1);

         List<String> aa = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog.");

        System.out.println(aa);

    }
}
