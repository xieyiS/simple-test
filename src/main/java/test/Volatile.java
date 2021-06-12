package test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 运行完这段代码之后，并不会获得期望的结果，而且会发现每次运行程序，输出的结果都不一样，都是一个小于200000的数字。
 *
 * 通过分析字节码我们知道，这是因为volatile只能保证可见性，无法保证原子性，而自增操作并不是一个原子操作
 *
 * 解决方法：
 *
 * 首先我们想到的是用synchronized来修饰increase方法。
 *
 * 使用synchronized修饰后，increase方法变成了一个原子操作，因此是肯定能得到正确的结果。
 *
 * 但是，我们知道，每次自增都进行加锁，性能可能会稍微差了点，有更好的方案吗？
 *
 * 答案当然是有的，这个时候我们可以使用Java并发包原子操作类（Atomic开头），例如以下代码。
 *
 * 我们将例子中的代码稍做修改：race改成使用AtomicInteger定义，“race++”改成使用“race.getAndIncrement()”,
 *
 * AtomicInteger.getAndIncrement()是原子操作，因此我们可以确保每次都可以获得正确的结果，并且在性能上有不错的提升（针对本例子，在JDK1.8.0_151下运行）。
 *
 * 通过方法调用，我们可以发现，getAndIncrement方法调用getAndAddInt方法，最后调用的是compareAndSwapInt方法，即CAS
 */
public class Volatile {
    //public static volatile int race = 0;
    public static AtomicInteger race = new AtomicInteger(0);

    private static final int THREADS_COUNT = 20;

    public static /*synchronized*/ void increase() {
        //race++;
        race.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
