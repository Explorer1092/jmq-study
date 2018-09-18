package com.jimingqiang.study.guava;

import com.google.common.base.Function;
import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by QDHL on 2018/9/16.
 *
 * @author mingqiang ji
 *
 * Multimap主要解决按k分组，如Map<K, List<V>>或Map<K, Set<V>>
 */
public class GuavaMultimap {


    @Test
    public void testMultiMap(){
        ListMultimap<String, People> multimap = ArrayListMultimap.create();
        People xiaoming = new People("xiaoming", 20);
        People xiaoli = new People("xiaoli", 30);
        multimap.put("xiaoming",xiaoming);
        multimap.put("xiaoming",xiaoming);
        multimap.put("xiaoming",xiaoming);
        multimap.put("xiaoming",xiaoming);
        multimap.put("xiaoli",xiaoli);

        System.out.println(multimap.size());
        List<People> xiaoming1 = multimap.get("xiaoming");
        System.out.println(xiaoming1);

        System.out.println(multimap.get("xiaoli"));

        System.out.println("========================================");

        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("Fruits", "Bannana");
        hashMultimap.put("Fruits", "Apple");
        hashMultimap.put("Fruits", "Pear");
        hashMultimap.put("Fruits", "Pear");
        hashMultimap.put("Vegetables", "Carrot");

        System.out.println(hashMultimap.size());
        System.out.println(hashMultimap.keySet().size());
        System.out.println(hashMultimap.containsKey("Fruits"));
        System.out.println(hashMultimap.get("Fruits").size());
        System.out.println(hashMultimap.get("Fruits"));


        System.out.println("========================================");

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

    }




     class People{

        private String name ;

        private int age;

         public People(String name, int age) {
             this.name = name;
             this.age = age;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public int getAge() {
             return age;
         }

         public void setAge(int age) {
             this.age = age;
         }
     }
}
