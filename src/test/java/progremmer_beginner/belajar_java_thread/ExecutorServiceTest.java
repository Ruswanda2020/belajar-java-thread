package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
    @Test
    void testExcecutorServiceSingel() throws InterruptedException {

        ExecutorService executors= Executors.newSingleThreadExecutor();
        for (var i = 0; i < 100; i++){
            executors.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("runable in thread " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
    @Test
    void testExcecutorServiceFix() throws InterruptedException {

        ExecutorService executors= Executors.newFixedThreadPool(10);
        for (var i = 0; i < 100; i++){
            executors.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("runable in thread " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executors.shutdown();
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
    @Test
    void testExcecutorServiceCached() throws InterruptedException {

        ExecutorService executors= Executors.newCachedThreadPool();
        for (var i = 0; i < 100; i++){
            executors.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("runable in thread " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executors.shutdown();
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
}