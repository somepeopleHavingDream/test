package org.yangxin.test.leetcode;

/**
 * 翻转单链表
 *
 * @author yangxin
 * 2020/01/07 11:47
 */
public class ReverseLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null, tmp;
        while (cur != null) {
            tmp = cur;
            cur = cur.next;

            tmp.next = pre;
            pre = tmp;
        }

        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        ListNode head = node1;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        System.out.println();
        head = reverseList(null);
//        head = reverseList(node1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
