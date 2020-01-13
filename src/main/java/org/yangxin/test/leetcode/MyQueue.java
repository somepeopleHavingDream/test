package org.yangxin.test.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 使用栈实现队列
 *
 * @author yangxin
 * 2020/01/13 11:03
 */
public class MyQueue {
    Deque<Integer> stack;

    public MyQueue() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        return stack.removeLast();
    }

    public int peek() {
        return stack.getLast();
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        queue.push(1);
        System.out.println(queue.stack);

        queue.push(2);
        System.out.println(queue.stack);

        System.out.println(queue.peek());

        System.out.println(queue.pop());

        System.out.println(queue.empty());
    }
}
