package org.yangxin.test.leetcode;

/**
 * 合并两个有序列表
 *
 * @author yangxin
 * 2019/10/22 10:54
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        ListNode result = mergeTwoLists(node1, node4);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
        System.out.println();
    }

    /**
     * 列表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);

        ListNode o1 = l1, o2 = l2, curr = listNode;
        while (o1 != null && o2 != null) {
            int o1Val = o1.val;
            int o2Val = o2.val;

            if (o1Val < o2Val) {
                curr.next = o1;
                curr = curr.next;
                o1 = o1.next;
            } else {
                curr.next = o2;
                curr = curr.next;
                o2 = o2.next;
            }
        }

        if (o1 == null) {
            curr.next = o2;
        } else {
            curr.next = o1;
        }

        return listNode.next;
    }
}
