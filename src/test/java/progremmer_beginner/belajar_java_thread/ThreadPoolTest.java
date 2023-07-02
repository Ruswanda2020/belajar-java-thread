package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//note
//jika ingin meruning task yang tidak mengambalikan value maka mengunakan runable
public class ThreadPoolTest {
    @Test
    void create() {
        var minPool=10;
        var maxPool=10;
        var alive=1;
        var aliveTime= TimeUnit.MINUTES;

        var queue=new ArrayBlockingQueue<Runnable>(100);

        var executor=new ThreadPoolExecutor(minPool,maxPool,alive,aliveTime,queue);
    }
    @Test
    void executeRunnable() throws InterruptedException {
        var minPool = 10;
        var maxPool = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(minPool, maxPool, alive, aliveTime, queue);

        executor.execute(() -> {
            try {
                Thread.sleep(5000L);
                System.out.println("Hallo from threadpool : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(6000L);
    }

    @Test
    void shutDown() throws InterruptedException {
        var minPool=10;
        var maxPool=100;
        var alive=1;
        var aliveTime= TimeUnit.MINUTES;

        var queue=new ArrayBlockingQueue<Runnable>(100  );

        var executor=new ThreadPoolExecutor(minPool,maxPool,alive,aliveTime,queue);
        for (var i=1 ; i <= 1000 ;i++){
             final var task=i;
            Runnable runnable=()->{
                try {
                    Thread.sleep(1000L);
                    System.out.println("task " + task + " from threadpool : "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            executor.execute(runnable);
        }
         //executor.shutdownNow();
        executor.awaitTermination(1,TimeUnit.DAYS);
    }
    @Test
    void rejected() throws InterruptedException {
        var logRejectedExecutionHandler =new LogRejectedExecutionHandler();
        var minPool=10;
        var maxPool=100;
        var alive=1;
        var aliveTime= TimeUnit.MINUTES;

        var queue=new ArrayBlockingQueue<Runnable>(10   );

        var executor=new ThreadPoolExecutor(minPool,maxPool,alive,aliveTime,queue,logRejectedExecutionHandler);
        for (var i=1 ; i <= 1000 ;i++){
            final var task=i;
            Runnable runnable=()->{
                try {
                    Thread.sleep(1000L);
                    System.out.println("task " + task + " from threadpool : "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            executor.execute(runnable);
        }
        //executor.shutdownNow();
        executor.awaitTermination(1,TimeUnit.DAYS);
    }

    public static class LogRejectedExecutionHandler implements RejectedExecutionHandler{
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("task  : "+r+ "is rejected");

        }
    }
}
