package com.jimingqiang.study.guava;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by QDHL on 2018/9/15.
 *
 * @author mingqiang ji
 *
 * 从实现上说，Ordering实例就是一个特殊的Comparator实例。
 * Ordering把很多基于Comparator的静态方法（如Collections.max）包装为自己的实例方法（非静态方法），
 * 并且提供了链式调用方法，来定制和增强现有的比较器。
 *
 * 当阅读链式调用产生的排序器时，应该从后往前读。之所以要从后往前读，是因为每次链式调用都是用后面的方法包装了前面的排序器
 * 装饰者模式
 */
public class GuavaOrdering {

    private static final Ordering<Number> numberOrdering = new NumberOrdering();

    public static void main(String[] args) {

        //testNatural();

        //testReverse();

        //testUsingToString();

        //testFrom();
        //testNullsFirst();

        //testNullsLast();

        //testCompound();

        //testGreatestOf();

        //testIsOrdered();

       // testGreatestOf();

        testMinAndMax();
    }

    /**
     * natural方法
     * 该方法使用自然排序规则生成排序器，如从小到大、日期先后顺序。使用这个方法之前先介绍一下onResultOf 方法，
     * 这个方法接收一个Function函数，该函数的返回值可以用于natural方法排序的依据，即根据这个返回值来进行自然排序
     *
     * sortedCopy方法会使用创建的排序器排序并生成一个新的List。对于Ordering.natural().onResultOf方法，
     * 阅读顺序是从后往前，即根据onResultOf 方法的返回值按照自然规则创建一个Ordering，然后调用sortedCopy方法排序并生成新List
     */
    public static void testNatural() {

        List<People> peopleList = new ArrayList<People>() {{
            add(new People("A", 33));
            add(new People("B", 11));
            add(new People("C", 18));
        }};

        Ordering<People> ordering = Ordering.natural().onResultOf(new Function<People, Comparable>() {

            @Override
            public Comparable apply(People people) {
                return people.age;
            }
        });

        for (People people : ordering.sortedCopy(peopleList)){
            System.out.println(MoreObjects.toStringHelper(people)
                    .add("name", people.getName())
                    .add("age", people.getAge())
            );
        }

    }


    /**
     * 这个方法使用反向的排序规则来排序，
     * 即若使用natural规则创建Ordering后，再接着调用reverse方法，则按照自然规则的反向，从大到小的规则排序
     */
    public static void testReverse() {

        List<People> peopleList = new ArrayList<People>() {{
            add(new People("A", 33));
            add(new People("B", 11));
            add(new People("C", 18));
        }};

        Ordering<People> ordering = Ordering.natural().reverse().onResultOf(new Function<People, Comparable>() {

            @Override
            public Comparable apply(People people) {
                return people.age;
            }
        });

        for (People people : ordering.sortedCopy(peopleList)){
            System.out.println(MoreObjects.toStringHelper(people)
                    .add("name", people.getName())
                    .add("age", people.getAge())
            );
        }

    }

    /**
     * 该方法创建Ordering，并根据排序依据值的toString方法值来使用natural规则排序
     */
    public static void testUsingToString() {
        List<People> peopleList = new ArrayList<People>() {{
            add(new People("A", 33));
            add(new People("B", 11));
            add(new People("C", 18));
        }};

        Ordering<People> ordering = Ordering.usingToString().onResultOf(new Function<People, Comparable>() {
            @Override
            public Comparable apply(People people) {
                return people.getName();
            }
        });

        for (People p : ordering.sortedCopy(peopleList)) {
            System.out.println(MoreObjects.toStringHelper(p)
                    .add("name", p.getName())
                    .add("age", p.getAge())
            );
        }


    }


    /**
     * 该方法接收一个自定义的Comparator比较器来创建Ordering，根据Comparator中的自定义规则排序
     */
    public static void testFrom() {
        List<People> peopleList = new ArrayList<People>() {{
            add(new People("A", 33));
            add(new People("B", 11));
            add(new People("C", 18));
        }};

        Ordering<People> ordering = Ordering.from(new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o1.getAge() - o2.getAge();
            }
        }).reverse();

        for (People p : ordering.sortedCopy(peopleList)) {
            System.out.println(MoreObjects.toStringHelper(p)
                    .add("name", p.getName())
                    .add("age", p.getAge())
            );
        }
    }


    /**
     * 使用当前排序器，但额外把null值排到最前面。
     */
    public static void testNullsFirst() {

        List<People> peopleList = new ArrayList<People>() {{
            add(new People(null, 33));
            add(new People("B", 11));
            add(new People("C", 18));
        }};

        Ordering<People> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<People, Comparable>() {

            @Override
            public Comparable apply(People people) {
                return people.name;
            }
        });

        sout( ordering,peopleList );

    }


    /**
     * 合成另一个比较器，以处理当前排序器中的相等情况。
     * 此方法中有两个name都为A，按name比较会相等，会继续按age的方式比较
     *
     * 用compound方法包装排序器时，就不应遵循从后往前读的原则。
     * 为了避免理解上的混乱，请不要把compound写在一长串链式调用的中间，你可以另起一行，在链中最先或最后调用compound。
     */
    public static void testCompound() {

        List<People> peopleList = new ArrayList<People>() {{
            add(new People("A", 33));
            add(new People("A", 11));
            add(new People("C", 18));
        }};

        Ordering<People> ordering = Ordering.natural().onResultOf(new Function<People, Comparable>() {

            @Override
            public Comparable apply(People people) {
                return people.age;
            }
        });

        Ordering<People> compound = Ordering.natural().onResultOf(new Function<People, Comparable>() {

            @Override
            public Comparable apply(People people) {
                return people.name;
            }
        }).compound(ordering);


        sout( compound,peopleList );

    }

    /**
     * 使用当前排序器，但额外把null值排到最前面。
     */
    public static void testNullsLast() {

        List<People> peopleList = new ArrayList<People>() {{
            add(new People(null, 33));
            add(new People("B", 11));
            add(new People("C", 18));
        }};

        Ordering<People> ordering = Ordering.natural().nullsLast().onResultOf(new Function<People, Comparable>() {

            @Override
            public Comparable apply(People people) {
                return people.name;
            }
        });

        sout( ordering,peopleList );

    }

    /**
     * 获取可迭代对象中最大的k个元素。对应的方法leastOf
     * greatestOf方法得结果为[4,4,3,3]
     * leastOf方法得结果为[1,2,2,3]
     */
    public static void testGreatestOf() {

        Ordering<Number> numberOrdering = new NumberOrdering();

        List<Integer> list = Arrays.asList(3, 1, 3, 2, 4, 2, 4, 3);
        List<Integer> integers = numberOrdering.greatestOf(list, 4);
        for (Integer integer : integers) {
            System.out.println(integer);
        }

        System.out.println("==================================================");
         integers = numberOrdering.leastOf(list, 4);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    /**
     * 判断可迭代对象是否已按排序器排序：允许有排序值相等的元素
     * 前两个都为false,后两个都为true
     *
     * isOrdered与isStrictlyOrdered的区别是比较方法，
     * isOrdered是compare(prev, next) > 0
     * isStrictlyOrdered是compare(prev, next) >= 0
     */
    public static void testIsOrdered() {

        System.out.println(numberOrdering.isOrdered(asList(5, 3, 0, 9)));
        System.out.println(numberOrdering.isOrdered(asList(0, 5, 3, 9)));
        System.out.println(numberOrdering.isOrdered(asList(0, 3, 5, 9)));
        System.out.println(numberOrdering.isOrdered(asList(0, 0, 3, 3)));
        System.out.println(numberOrdering.isOrdered(asList(0, 3)));

        System.out.println("==================================================");
        System.out.println(numberOrdering.isStrictlyOrdered(asList(5, 3, 0, 9)));
        System.out.println(numberOrdering.isStrictlyOrdered(asList(0, 5, 3, 9)));
        System.out.println(numberOrdering.isStrictlyOrdered(asList(0, 3, 5, 9)));
        System.out.println(numberOrdering.isStrictlyOrdered(asList(0, 0, 3, 3)));
        System.out.println(numberOrdering.isStrictlyOrdered(asList(0, 3)));

    }


    /**
     * min返回两个参数中最小的那个。如果相等，则返回第一个参数。
     * max返回两个参数中最大的那个。如果相等，则返回第一个参数。
     */
    public static void testMinAndMax() {

        List<Integer> ints = Lists.newArrayList(5, 3, 0, 9);
        System.out.println(numberOrdering.max(ints));
        System.out.println(numberOrdering.min(ints));

        System.out.println("==================================================");
        // when the values are the same, the first argument should be returned
        Integer a = new Integer(4);
        Integer b = new Integer(5);
        ints = Lists.newArrayList(a, b, b);
        System.out.println(numberOrdering.min(ints.iterator()));
        System.out.println(numberOrdering.max(ints.iterator()));
        System.out.println(numberOrdering.min(a,b));
        System.out.println(numberOrdering.max(a,b));
        System.out.println("==================================================");

        System.out.println(numberOrdering.min(9, 3, 0, 5, 8));
        System.out.println(numberOrdering.max(5, 9, 0, 3, 8));


    }




    public static  void sout(Ordering<People> ordering,List<People> peopleList ){

        for (People people: ordering.sortedCopy(peopleList)){
            System.out.println(MoreObjects.toStringHelper(people)
                    .add("name", people.getName())
                    .add("age", people.getAge())
            );
        }
    }




    private static class NumberOrdering extends Ordering<Number> {
        @Override
        public int compare(Number a, Number b) {
            return ((Double) a.doubleValue()).compareTo(b.doubleValue());
        }

        @Override
        public int hashCode() {
            return NumberOrdering.class.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof NumberOrdering;
        }

        private static final long serialVersionUID = 0;
    }



        static class People{
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

            @Override
            public String toString() {
                return "People{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }
}
