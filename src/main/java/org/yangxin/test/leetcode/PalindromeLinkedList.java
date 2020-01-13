package org.yangxin.test.leetcode;

/**
 * 回文链表
 *
 * @author yangxin
 * 2020/01/13 11:17
 */
public class PalindromeLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        // 先找到该链表的中点，第一个while循环结束后，slow就是该单链表的中点，也是前区间的终点
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next != null ? fast.next.next : null;
        }

        // 将后面区间的节点翻转，此while循环结束后pre指向了最后的一个节点
        ListNode pre = null, cur = slow;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        ListNode it = pre;
        while (it != null) {
            System.out.println(it.val);
            it = it.next;
        }

        // 比对
        while (head != null && pre != null) {
            if (head.val != pre.val) {
                return false;
            }

            head = head.next;
            pre = pre.next;
        }

        return true;
    }

    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        node1.next = new ListNode(2);
//        System.out.println(isPalindrome(node1));

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(2);
//        ListNode node4 = new ListNode(1);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        System.out.println(isPalindrome(node1));

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(-1);
        ListNode node4 = new ListNode(-1);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        System.out.println(isPalindrome(node1));
    }
}
