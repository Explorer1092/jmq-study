package com.jimingqiang.study.guava;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by QDHL on 2018/9/17.
 *
 * @author mingqiang ji
 */
public class GuavaMultimaps {

    @Test
    public void testMultiMap(){




        /**
         * 有一组对象，它们在某个属性上分别有独一无二的值，而我们希望能够按照这个属性值查找对象——译者注：
         * 这个方法返回一个Map，键为Function返回的属性值，值为Iterable中相应的元素，因此我们可以反复用这个Map进行查找操作。
         */
        ImmutableSet digits0 = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        ImmutableMap immutableMap = Maps.uniqueIndex(digits0, new Function<String, String>() {

            @Override
            public String apply(String input) {
                return input;
            }
        });

        System.out.println(immutableMap);

        System.out.println("========================================");


        /**
         * 作为Maps.uniqueIndex的兄弟方法，Multimaps.index(Iterable, Function)通常针对的场景是：
         * 有一组对象，它们有共同的特定属性，我们希望按照这个属性的值查询对象，但属性值不一定是独一无二的。
         */
        ImmutableSet digits = ImmutableSet.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            public Integer apply(String string) {
                return string.length();
            }
        };

        ImmutableListMultimap index = Multimaps.index(digits, lengthFunction);

        System.out.println(index);


        System.out.println("==================invertFrom======================");

        /**
         * 将多对一转换成一对多
         */
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2, 4, 6));
        multimap.putAll("a", Ints.asList(4, 2, 1));
        multimap.putAll("c", Ints.asList(2, 5, 3));
        TreeMultimap<Integer, String> inverse  = Multimaps.invertFrom(multimap, TreeMultimap.create());
        System.out.println(inverse);



        System.out.println("==================forMap======================");
        /**
         * 想在Map对象上使用Multimap的方法吗？forMap(Map)把Map包装成SetMultimap。
         * 这个方法特别有用，例如，与Multimaps.invertFrom结合使用，可以把多对一的Map反转为一对多的Multimap。
         */
        Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);
        SetMultimap<String, Integer> stringIntegerSetMultimap = Multimaps.forMap(map);
        System.out.println(stringIntegerSetMultimap);

        HashMultimap<Integer, String> integerStringHashMultimap = Multimaps.invertFrom(stringIntegerSetMultimap, HashMultimap.create());

        System.out.println(integerStringHashMultimap);


    }
}
