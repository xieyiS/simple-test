package test;

/**
 * 是否可以从一个静态（static）方法内部发出对非静态（non-static）方法的调用？
 */
public class StaticTest {
    /**
     * 1）没有显示创建对象实例：不可以发起调用，非静态方法只能被对象所调用，静态方法可以通过对象调用，也可以通过类名调用，
     * 所以静态方法被调用时，可能还没有创建任何实例对象。
     * 因此通过静态方法内部发出对非静态方法的调用，此时可能无法知道非静态方法属于哪个对象。
     */

    /**
     * 2）显示创建对象实例：可以发起调用，在静态方法中显示的创建对象实例，则可以正常的调用。
     */
    public static void staticMethod() {
        // 先创建实例对象，再调用非静态方法：成功执行
        StaticTest demo = new StaticTest();
        demo.instanceMethod();
    }
    public void instanceMethod() {
        System.out.println("非静态方法");
    }
}
