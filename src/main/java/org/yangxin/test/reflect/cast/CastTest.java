package org.yangxin.test.reflect.cast;

/**
 * Class.cast(Object obj)方法，作用就是强制类型转换，将obj转换成T类型
 *
 * @author yangxin
 * 2021/3/6 下午4:22
 */
@SuppressWarnings({"RedundantClassCall", "UnnecessaryLocalVariable"})
public class CastTest {

    public static void main(String[] args) {
        AppleTree appleTree = new AppleTree();

        Tree one = Tree.class.cast(appleTree);
        one.print();

        // 强制类型转换
        Tree force = appleTree;
        force.print();

        BananaTree two = BananaTree.class.cast(appleTree);
        two.print();
    }
}
