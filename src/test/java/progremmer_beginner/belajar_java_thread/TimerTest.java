package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class TimerTest {

    @Test
    void deleyedJob() throws InterruptedException {

        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Deleyed Job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 3000L);

        Thread.sleep(4000L);
    }

    @Test
    void deleyedPariodic() throws InterruptedException {

        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Deleyed Job");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 3000L, 4000L);

        Thread.sleep(15000L);
    }


}
