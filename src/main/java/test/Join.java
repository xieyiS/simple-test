package test;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * 线程的 join() 方法是干啥用的？
 * 用于等待当前线程终止。如果一个线程A执行了 threadB.join() 语句，其含义是：
 * 当前线程A等待 threadB 线程终止之后才从 threadB.join() 返回继续往下执行自己的代码。
 */
public class Join implements Runnable{

    private String name;

    public Join(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        System.out.printf("%s begins: %s\n", name, LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s has finished: %s\n", name, LocalTime.now());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Join("One"));
        Thread thread2 = new Thread(new Join("Two"));
        thread1.start();
        thread2.start();

        /**
         * 让main等待thread1、thread2执行完毕,main再继续执行
         */
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread is finished");
    }

}
