package test;

/**
 * 初始化考察，请指出下面程序的运行结果
 */
public class Initial {
    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
    }
}

class A {
    static { // 父类静态代码块
        System.out.print("A");
    }

    public A() { // 父类构造器
        System.out.print("a");
    }
}

class B extends A {
    static { // 子类静态代码块
        System.out.print("B");
    }

    public B() { // 子类构造器
        System.out.print("b");
    }
}
/**
 * 1）静态变量只会初始化（执行）一次。
 *
 * 2）当有父类时，完整的初始化顺序为：父类静态变量（静态代码块）->子类静态变量（静态代码块）
 *                               ->父类非静态变量（非静态代码块）->父类构造器
 *                               ->子类非静态变量（非静态代码块）->子类构造器 。
 */
