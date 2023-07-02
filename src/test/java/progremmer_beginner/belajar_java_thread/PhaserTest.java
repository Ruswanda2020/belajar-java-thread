package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {
    @Test
    void countDownLatch() throws InterruptedException {

        final var phaser=new Phaser();
        final var executor= Executors.newFixedThreadPool(15);

        phaser.bulkRegister(5);
        phaser.bulkRegister(5);
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                try {
                    System.out.println("start task");
                    Thread.sleep(200);
                    System.out.println("finish task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    phaser.arrive();
                }
            });
        }
        executor.execute(()->{
            phaser.awaitAdvance(0);
            System.out.println("all task finish");
        });

        executor.awaitTermination(1, TimeUnit.DAYS);


    }
}
