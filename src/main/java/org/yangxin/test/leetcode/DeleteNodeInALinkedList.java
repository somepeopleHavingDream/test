package org.yangxin.test.leetcode;

/**
 * 在链表中删除节点
 *
 * @author yangxin
 * 2020/01/15 17:05
 */
public class DeleteNodeInALinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        deleteNode(node1);

        ListNode cur = node1;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
