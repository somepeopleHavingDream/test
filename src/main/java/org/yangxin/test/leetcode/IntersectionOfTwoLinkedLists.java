package org.yangxin.test.leetcode;

/**
 * 两个单链表的交汇节点
 *
 * @author yangxin
 * 2019/11/13 08:48
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(0);
        ListNode b3 = new ListNode(1);

        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(4);
        ListNode c3 = new ListNode(5);

        a1.next = a2;

        b1.next = b2;
        b2.next = b3;

        a2.next = c1;
        b3.next = c1;
        c1.next = c2;
        c2.next = c3;

        ListNode result = getIntersectionNode(a1, b1);
        System.out.println(result == null ? result : result.val);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;
    }
}
