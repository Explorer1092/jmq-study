package com.jimingqiang.study.guava;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import org.junit.jupiter.api.Test;

/**
 * Created by QDHL on 2018/9/16.
 *
 * @author mingqiang ji
 */
public class GuavaClassToInstanceMap {

    @Test
    public void testClassToInstanceMap(){

        ClassToInstanceMap<A> classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.put(A.class, new A());
        classToInstanceMap.put(A_1.class, new A_1());
        classToInstanceMap.put(A_2.class, new A_2());
        System.out.println(classToInstanceMap);
        System.out.println("A_1 Object: " + classToInstanceMap.get(A_1.class));
        try {
            classToInstanceMap.put(A_1.class,  new A_2());
        } catch (ClassCastException e) {
            System.out.println(e);
        }
        System.out.println("set A_1 Object to null");
        classToInstanceMap.put(A_1.class, null);
        System.out.println("get A_1 Object: " + classToInstanceMap.get(A_1.class));

        ClassToInstanceMap<Number> intMap = MutableClassToInstanceMap.create();
        intMap.put(int.class, 2);
        intMap.put(double.class, 3.2);
        System.out.println("get int value: " + intMap.get(int.class));
        System.out.println("get double value: " + intMap.get(double.class));


    }


    class A {
        public String toString() {
            return "A";
        }
    }

    class A_1 extends A {
        public String toString() {
            return "A_1";
        }
    }

    class A_2 extends A {
        public String toString() {
            return "A_2";
        }
    }
}
