package progremmer_beginner;

import org.junit.jupiter.api.Test;
import progremmer_beginner.belajar_java_thread.Counter;
import progremmer_beginner.belajar_java_thread.SynchronizationCounter;

public class SynchronizationTest {
    @Test
    void testRaceCondition() throws InterruptedException {
        var counter=new SynchronizationCounter();
        Runnable runnable=new Thread(()->{
            for (var i = 1 ; i <= 1_000_000 ; i++){
                counter.increment();
            }
        });

        var thread1=new Thread(runnable);
        var thread2=new Thread(runnable);
        var thread3=new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getConter());
    }
}
