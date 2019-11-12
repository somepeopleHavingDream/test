package org.yangxin.test.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小栈
 *
 * @author yangxin
 * 2019/11/12 10:21
 */
public class MinStack {
    private List<Integer> elements = new ArrayList<>();
    private Integer minIndex = -1;

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
//        System.out.println(minStack.elements);
        minStack.push(0);
//        System.out.println(minStack.elements);
        minStack.push(-3);
        int min = minStack.getMin();
//        System.out.println(minStack.elements);
        System.out.println(min);
        minStack.pop();
        System.out.println(minStack.elements);
        int top = minStack.top();
        System.out.println(top);
        System.out.println(minStack.elements);
        int min1 = minStack.getMin();
        System.out.println(min1);
    }

    private void pop() {
        if (elements.size() != 0) {
            elements.remove(elements.size() - 1);

            minIndex = 0;

            int min = elements.get(0);
            for (int i = 1; i < elements.size(); i++) {
                if (elements.get(i) < min) {
                    minIndex = i;
                }
            }
        }
    }

    private int getMin() {
        return elements.get(minIndex);
    }

    private void push(int i) {
        if (elements.size() == 0) {
            elements.add(i);
            minIndex = elements.size() - 1;
            return;
        }

        if (i > top()) {
            elements.add(i);
            return;
        }

        // 向后添加一个元素
        elements.add(i);
        minIndex = elements.size() - 1;
    }

    private int top() {
        return elements.get(elements.size() - 1);
    }

    private MinStack() {

    }
}
