package org.yangxin.test.jvm.compileroptimize;

import java.util.Arrays;
import java.util.List;

/**
 * 自动装箱、拆箱与遍历循环
 * 
 * @author yangxin
 * 2020/07/20 10:34
 */
public class BoxingUnBoxing {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        
        // 如果在JDK1.7中，还有另外一颗语法糖（此语法糖随着Project Coin一起被划分到JDK1.8中了，在JDK1.7里不会包括。
        // 能让上面这句代码进一步简写成List<Integer> list = {1, 2, 3, 4};
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);
    }
}
