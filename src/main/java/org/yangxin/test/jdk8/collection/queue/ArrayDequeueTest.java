package org.yangxin.test.jdk8.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yangxin
 * 2022/2/18 16:49
 */
public class ArrayDequeueTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        Deque<String> queue = new ArrayDeque<>();
        queue.offer("光头强");
        queue.offer("熊大");
        queue.offer("熊二");

        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }

    private static void test1() {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("循序渐进Linux");
        stack.push("小学语文");
        stack.push("时间简史");

        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
