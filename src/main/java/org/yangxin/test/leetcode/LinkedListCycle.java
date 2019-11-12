package org.yangxin.test.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个链表是否有环
 *
 * @author yangxin
 * 2019/11/12 09:56
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        ListNode node5 = new ListNode(1);
//        ListNode node6 = new ListNode(2);
//        node5.next = node6;
//        node6.next = node5;
        boolean result = hasCycle(node5);
        System.out.println(result);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        ListNode p = head;
        while (p != null) {
            if (set.contains(p)) {
                return true;
            } else {
                set.add(p);
                p  = p.next;
            }
        }
        return false;
    }
}
