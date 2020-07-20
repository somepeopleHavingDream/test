package org.yangxin.test.jvm.compileroptimize;

/**
 * Java语言的条件编译
 * 只能使用条件为常量的if语句才能达到效果，如果使用常量与其他带有条件判断能力的语句搭配，则可能在控制流分析中提示错误，被拒绝编译。
 *
 * @author yangxin
 * 2020/07/20 10:42
 */
public class ConditionCompiler {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        if (true) {
            System.out.println("block 1");
        } else {
            System.out.println("block 2");
        }
    }
}
