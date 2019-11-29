package org.yangxin.test.leetcode;

/**
 * 移除所有值等于val的链表元素
 *
 * @author yangxin
 * 2019/11/29 15:21
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode node8 = new ListNode(1);
        ListNode node9 = new ListNode(2);
        node8.next = node9;

        ListNode listNode = removeElements(node8, 1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                if (pre == null) {
                    head = cur.next;
                    cur = cur.next;
                    continue;
                }

                pre.next = cur.next;
                cur.next = null;
                cur = pre.next;
                continue;
            }

            pre = cur;
            cur = cur.next;
        }

        return head;
    }
}
