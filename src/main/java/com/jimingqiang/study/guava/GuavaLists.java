package com.jimingqiang.study.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by QDHL on 2018/9/18.
 *
 * @author mingqiang ji
 */
public class GuavaLists {

    /**
     * 对List进行切割
     */
    @Test
    public void testPartition() {
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4,5);
        List<List<Integer>> subLists = Lists.partition(numbers, 2);
        List<Integer> s1 = Lists.newArrayList(1, 2);
        List<Integer> s2 = Lists.newArrayList(3, 4);
        List<Integer> s3 = Lists.newArrayList(5);
        assertEquals(subLists.get(0), s1);
        assertEquals(subLists.get(1), s2);
        assertEquals(subLists.get(2), s3);
    }


    /**
     * 对List进行反转
     */
    @Test
    public void testReverse() {
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4);
        List<Integer> expected = Lists.newArrayList(4, 3, 2, 1);
        List<Integer> reversed = Lists.reverse(numbers);
        assertEquals(expected, reversed);
    }

    /**
     * 进List进行转换
     * @throws Exception
     */
    @Test
    public void testGainNameForUser() throws Exception {
        User user1 = new User("carl",12);
        User user2 = new User(null,12);
        User user3 = new User("",12);
        User user4 = new User("kevin",12);

        List<User> users = Lists.newArrayList(user1, user2, user3, user4);
        /*List<String> names = Lists.transform(users, new Function<User, String>() {
            @Override
            public String apply(User input) {
                return input.getName();
            }
        });

        Collection<String> result = Collections2.filter(names, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input != null && !"".equals(input);
            }
        }
);*/

       /* Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input != null && !"".equals(input);
            }
        };*/

        Predicate<User> predicate = new Predicate<User>() {
            @Override
            public boolean apply(User input) {
                return input.getName() != null && !"".equals(input.getName());
            }
        };


        Function<User, String> function = new Function<User, String>() {
            @Override
            public String apply(User input) {
                return input.getName();
            }
        };
        Collection<String> result = FluentIterable.from(users)
                .filter(predicate)
                .transform(function)
                .toList();


        // Collection<String> result = Collections2.filter(names, Predicates.notNull());
        //ArrayList<String> strings = Lists.newArrayList(result);

        for (String name : result) {
            System.out.println(name);
        }
        //System.out.println(strings.size());
        //assertEquals(names, Lists.newArrayList("carl", "erin", "kevin", "kevin"));
    }


    class User{

        private String name;

        private int age;

        public User(String name, int age) {
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
