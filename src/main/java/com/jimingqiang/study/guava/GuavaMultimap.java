package com.jimingqiang.study.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
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
