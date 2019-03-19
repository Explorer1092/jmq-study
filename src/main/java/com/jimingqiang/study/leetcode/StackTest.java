package com.jimingqiang.study.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackTest {

    private static Map<Character,Character>  mappings = new HashMap<Character,Character>();

    static {
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
    }


    public static boolean isValid(String s){

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if(mappings.containsKey(s.charAt(i))){
                char value = mappings.get(s.charAt(i));
                char pop = stack.isEmpty() ? '#' :stack.pop();
                if(value != pop){
                    return false;
                }

            }else{
                stack.push(s.charAt(i));
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[}"));
    }
}
