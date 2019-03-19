package com.jimingqiang.study.leetcode;

public class LinkeListToQuene {

    private Node head ;

    private Node tail;

    int size = 0;//记录队列长度

    public LinkeListToQuene() {
        this.head = null;
        this.tail = null;
    }

    public boolean enqueue(Object object){
        if( size == 0 ){
            head = new Node(object);
            tail = head;
            size++;
            return true;

        }

        Node node = new Node(object, null);
        tail.setNextNode(node);
        tail = node;
        size++;

        return true;


    }


    public boolean deleteQueue(){
        if(size == 0){
            return false;
        }

        tail = tail.getNextNode();
        size--;

        return true;
    }










    private class Node{

        private Object data;

        private Node nextNode;

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
