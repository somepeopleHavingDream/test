package org.yangxin.test.leetcode;

/**
 * 删除链表中重复的元素
 *
 * @author yangxin
 * 2019/10/29 09:09
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = deleteDuplicates(node1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    private static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode curr = head;
        ListNode succ = head.next;
        while (curr != null && succ != null) {
            // 两个元素不相同
            if (curr.val != succ.val) {
                curr = curr.next;
                succ = succ.next;
                continue;
            }

            // 两个元素相同
            curr.next = succ.next;
            succ.next = null;
            succ = curr.next;
        }
        return head;
    }
}
