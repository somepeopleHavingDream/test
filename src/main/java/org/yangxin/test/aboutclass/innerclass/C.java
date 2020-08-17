package org.yangxin.test.aboutclass.innerclass;

/**
 * 内部类的权限究竟有多大？
 *
 * @author yangxin
 * 2020/08/17 10:31
 */
public class C {

    int a = 1;
    private int b = 2;
    protected int c = 2;
    public int d = 4;

    void a() {
        System.out.println("A: " + a);
    }

    private void b() {
        System.out.println("B: " + b);
    }

    protected void c() {
        System.out.println("C: " + c);
    }

    public void d() {
        System.out.println("D: " + d);
    }

    /**
     * @author yangxin
     * 2020/08/14 10:36
     */
    class D {

        void show() {
            int max = a + b + c + d;

            a();
            b();
            c();
            d();
            System.out.println("Max: " + max);
        }
    }

    public static void main(String[] args) {
        D d = new C().new D();
        d.show();
    }
}
