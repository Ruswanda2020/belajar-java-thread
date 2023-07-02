package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    @Test
    void testlocalthread() throws InterruptedException {
        var rendom=new Random();
        var userService=new UserService();
        final var executor= Executors.newFixedThreadPool(10);

        for (int i = 0; i <= 50; i++) {
            var index=i;
            executor.execute(()->{
                try {
                    userService.setUser("user "+index);
                    Thread.sleep(1000,rendom.nextInt(3000));
                    userService.doAction();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
