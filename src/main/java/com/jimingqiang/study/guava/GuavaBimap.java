package com.jimingqiang.study.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;

/**
 * Created by QDHL on 2018/9/16.
 *
 * @author mingqiang ji
 *
 * BiMap提供了一种新的集合类型，它提供了key和value的双向关联的数据结构。
 * 通常情况下，我们在使用Java的Map时，往往是通过key来查找value的，
 * 但是如果我们需要value来查找key时,我们可以利用Bimap来实现
 *
 *
 *
 */
public class GuavaBimap {

    @Test
    public void testBimap(){

        HashBiMap<Integer, String> hashBiMap = HashBiMap.create();
        hashBiMap.put(1,"a.log");
        hashBiMap.put(2,"b.log");
        hashBiMap.put(3,"c.log");
        hashBiMap.put(4,"d.log");
        //hashBiMap.put(5,"d.log");
        //在BiMap中，如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常。
        // 如果对特定值，你想要强制替换它的键，请使用 BiMap.forcePut(key, value)。
        //{5=d.log, 3=c.log, 2=b.log, 1=a.log},通过结果集我们可以看到
        //key=4的键值对没有了，只有key=5的键值对，前面的key也会被覆盖了
        //hashBiMap.forcePut(5,"d.log");

        /**
         * inverse方法会返回一个反转的BiMap，但是注意这个反转的map不是新的map对象，
         * 它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象
         */

        System.out.println(hashBiMap);

        BiMap<String, Integer> inverse = hashBiMap.inverse();
        System.out.println(inverse);

        System.out.println("===================================");

        hashBiMap.put(5,"e.log");
        System.out.println(hashBiMap);
        System.out.println(inverse);

        System.out.println("===================================");

        inverse.put("f.log",6);
        System.out.println(hashBiMap);
        System.out.println(inverse);
    }


}
