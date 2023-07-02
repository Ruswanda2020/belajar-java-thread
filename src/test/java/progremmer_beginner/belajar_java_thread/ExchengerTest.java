package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchengerTest {
    @Test
    void test() throws InterruptedException {
        final var excenger=new Exchanger<String>();
        final var executor= Executors.newFixedThreadPool(10);

        executor.execute(()->{
            try {
                System.out.println("thread 1 send : first");
                Thread.sleep(1000);
                var result =excenger.exchange("first");
                System.out.println("thread 1 Receive : "+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(()->{
            try {
                System.out.println("thread 2 send : secound");
                Thread.sleep(3000);
                var result=excenger.exchange("secound");
                System.out.println("thread 2 Receive : "+ result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
