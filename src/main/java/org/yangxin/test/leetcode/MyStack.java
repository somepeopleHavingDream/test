package org.yangxin.test.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用队列实现栈
 *
 * @author yangxin
 * 2020/01/08 11:30
 */
public class MyStack {
    Deque<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.push(x);
    }

    public int pop() {
        return queue.pop();
    }

    public int top() {
        return queue.element();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.queue.forEach(System.out::print);

        System.out.println();
        int pop = myStack.pop();
        myStack.queue.forEach(System.out::print);

        System.out.println();
        int top = myStack.top();
        System.out.println(top);

        System.out.println(myStack.empty());
    }
}
