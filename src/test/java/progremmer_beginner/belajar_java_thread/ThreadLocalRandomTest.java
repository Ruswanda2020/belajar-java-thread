package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalRandomTest {
    @Test
    void test() throws InterruptedException {
        final var executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i <= 100; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(2000);
                    var number= ThreadLocalRandom.current().nextInt();
                    System.out.println(number);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void stream() throws InterruptedException {
        final var executor=Executors.newFixedThreadPool(100);
        executor.execute(()->{
            ThreadLocalRandom.current().ints(100,0,100)
                    .forEach(System.out::println);
        });
        executor.shutdown();
        executor.awaitTermination(1,TimeUnit.DAYS);
    }
}
