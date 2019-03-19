package com.jimingqiang.study.leetcode;

public class CircularQueue {

    private Object[] array;

    private int n ;

    private int tail;

    private int head;

    public CircularQueue(int n) {
        this.array = new Object[n];
        this.n = n;
        this.tail = 0;
        this.head = 0;
    }

    /**
     * 入队
     * @return
     */
    public boolean enquene(Object ele){
        //队列满了
        if((tail+1) % n == head){
           return false;
        }
        array[tail] = ele;
        //tail 往前走一步
        tail = (tail+1) % n;
        return true;

    }

    public Object dequene(){
        //队列为空
        if(tail == head){
            return false;
        }
        Object ele = array[head];
        head = (head+1) % n;

        return ele;
    }
}
