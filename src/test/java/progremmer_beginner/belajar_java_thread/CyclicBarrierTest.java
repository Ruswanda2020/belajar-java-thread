package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {
    @Test
    void test() throws InterruptedException {

        final var cylicBarrier=new CyclicBarrier(5);
        final var executore= Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executore.execute(()->{
                try {
                    System.out.println("waiting");
                    cylicBarrier.await();
                    System.out.println("Done waiting");
                } catch (InterruptedException |BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executore.awaitTermination(1, TimeUnit.DAYS);
    }
}
//cyclicbarrier menunggu semua thread nya siap dulu lalu jalan bareng semua threadnya.