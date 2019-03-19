package com.jimingqiang.study.leetcode;

public class ArrayToStack {

    private Object[] array ;
    private int count;
    private int n;

    public ArrayToStack(int n) {
        array = new Object[n];
        this.n = n;
        count = 0;
    }


    public boolean push(Object object){
        if(count == n){
            return false;
        }
        array[count] = object;
        count++;
        return true;

    }

    public Object pop(){
        if(count == 0){
            return null;
        }

        Object object = array[count-1];
        count--;
        return object;
    }
}
