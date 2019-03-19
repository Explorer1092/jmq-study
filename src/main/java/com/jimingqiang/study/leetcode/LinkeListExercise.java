package com.jimingqiang.study.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LinkeListExercise {

    /**
     * 单链表反转
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     *
     * leetcode  206
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode curr = head;

        while( curr != null ){
            ListNode nextTemp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextTemp;
        }

        return pre;
    }

    /**
     * 链表中环的检测
     *
     * leetcode 141
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
       Set<ListNode> nodesSeen = new HashSet<>();

       while(head != null){
           if(nodesSeen.contains(head)){
               return true;
           }else{
               nodesSeen.add(head);
           }

           head = head.next;

       }

       return true;

    }









    private class ListNode{
        private Object data;

        private ListNode next;

        public ListNode(Object data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

}



