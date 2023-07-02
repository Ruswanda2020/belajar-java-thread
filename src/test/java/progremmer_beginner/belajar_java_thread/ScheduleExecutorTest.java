package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorTest {
    @Test
    void deleyJob() throws InterruptedException {
        var executore = Executors.newScheduledThreadPool(10);
        var future =executore.schedule(()-> System.out.println("hallo schedule"),5, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.MILLISECONDS));
        executore.awaitTermination(1,TimeUnit.DAYS);
    }

    @Test
    void PeriodicJob() throws InterruptedException {
        var executore = Executors.newScheduledThreadPool(10);
        var future =executore.scheduleAtFixedRate(
                ()-> System.out.println("hallo schedule"),2,2, TimeUnit.SECONDS);

        System.out.println(future.getDelay(TimeUnit.MILLISECONDS));
        executore.awaitTermination(1,TimeUnit.DAYS);

    }
}

