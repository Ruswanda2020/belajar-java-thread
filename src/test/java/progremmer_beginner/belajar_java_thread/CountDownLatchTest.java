package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    @Test
    void test() throws InterruptedException {
        final var counDownLatch=new CountDownLatch(5);
        final var executors= Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executors.execute(()->{
                try {
                    System.out.println("start task");
                    Thread.sleep(2000);
                    System.out.println("finish task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    counDownLatch.countDown();
                }
            });
        }
        executors.execute(()->{
            try {
                counDownLatch.await();
                System.out.println("all task finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
}
