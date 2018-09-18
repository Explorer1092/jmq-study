package com.jimingqiang.study.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by QDHL on 2018/9/18.
 *
 * @author mingqiang ji
 */
public class GuavaSets {

    /**
     * 笛卡尔集
     * [Bar, Bob]
     * [Bar, Jim]
     * [Foo, Bob]
     * [Foo, Jim]
     */
    @Test
    public void testCartesianProduct(){
        Set<String> s1 = Sets.newHashSet("Foo","Bar");
        Set<String> s2 = Sets.newHashSet("Jim","Bob");
        Set<List<String>> cartesian  = Sets.cartesianProduct(s1, s2);
        for (List<String> strings : cartesian) {

            System.out.println(strings);

        }
        List<String> list = Lists.newArrayList();
        list.add("Foo");
        list.add("Jim");
        assertEquals(cartesian.contains(list),true);
        list.clear();
        list.add("Foo");
        list.add("Bob");
        assertEquals(cartesian.contains(list),true);
        list.clear();
        list.add("Bar");
        list.add("Jim");
        assertEquals(cartesian.contains(list),true);
        list.clear();
        list.add("Bar");
        list.add("Bob");
        assertEquals(cartesian.contains(list),true);
    }


    /**
     * 返回给定集合的所有子集
     * []
     * [Bar]
     * [Foo]
     * [Bar, Foo]
     * [Jim]
     * [Bar, Jim]
     * [Foo, Jim]
     * [Bar, Foo, Jim]
     */
    @Test
    public void testPowerSet(){
        Set<String> s1 = Sets.newHashSet("Foo","Bar","Jim");
        Set<Set<String>> sets = Sets.powerSet(s1);
        for (Set<String> set : sets) {
            System.out.println(set);
        }

    }

    /**
     * 返回2个set的并集
     * [1,2,3,4]
     */
    @Test
    public void testUnion(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("3","2","4");
        Sets.SetView<String> union = Sets.union(s1, s2);

        for (String s : union) {
            System.out.println(s);

        }
    }


    /**
     * 返回2个set的交集
     */
    @Test
    public void testIntersection(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("3","2","4");
        Sets.SetView<String> sv = Sets.intersection(s1,s2);

        for (String s : sv) {
            System.out.println(s);

        }
    }

    /**
     * 比较两个集合的不同的值
     */
    @Test
    public void testSetDifference() {
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("3","2","4");
        //判断s1与s2不同的值，返回s2不存在，s1存在的值的集合
        Sets.SetView<String> sv = Sets.difference(s1,s2);
        for (String s : sv) {
            System.out.println(s);

        }
       //判断s2与s1不同的值，返回s1不存在，s2存在的值的集合
        sv = Sets.difference(s2,s1);
        for (String s : sv) {
            System.out.println(s);

        }
    }

    /**
     * 返回2个set，共同元素以外的所有元素
     * [1,4]
     */
    @Test
    public void testSymmetricDifference(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("3","2","4");
        Sets.SetView<String> sv = Sets.symmetricDifference(s1,s2);
        for (String s : sv) {
            System.out.println(s);
        }
        //assertThat(sv.size()==2 && sv.contains("1") && sv.contains("4"),is(true));
    }
}
