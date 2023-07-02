package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    @Test
    void test() throws InterruptedException {
         final var semaphore=new Semaphore(20);
         final var executores= Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executores.execute(()->{
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }

        executores.awaitTermination(1, TimeUnit.DAYS);
    }
}
