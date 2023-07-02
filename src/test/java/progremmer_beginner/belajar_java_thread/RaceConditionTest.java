package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

public class RaceConditionTest {
    @Test
    void testRaceCondition() throws InterruptedException {
        var counter=new Counter();
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
