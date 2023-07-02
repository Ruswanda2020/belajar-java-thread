package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

public class MainApplicationTest {
    @Test
    void mainThread() {
        var name=Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread() {
        Runnable runnable= () -> {
            System.out.println("hallo from thread : "+Thread.currentThread().getName());
        };

        var thread=new Thread(runnable);
        thread.start();
        System.out.println("program selesai");

    }

    @Test
    void threadSleep()throws InterruptedException{
        Runnable runnable= () -> {
            try {
                Thread.sleep(2_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hallo from thread : "+Thread.currentThread().getName());
        };
        var thread=new Thread(runnable);
        thread.start();
        System.out.println("program selesai");

        Thread.sleep(3_000L);

    }

    @Test
    void threadJoin()throws InterruptedException{
        Runnable runnable= () -> {
            try {
                Thread.sleep(2_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hallo from thread : "+Thread.currentThread().getName());
        };
        var thread=new Thread(runnable);
        thread.start();
        System.out.println("menunggu selesai");
        thread.join();
        System.out.println("program selesai");



    }
    @Test
    void threadInterruptIncorrect()throws InterruptedException{
        Runnable runnable= () -> {
            for (var i =1 ; i < 10 ; i++) {
                System.out.println("runnable ke : " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        var thread=new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("menunggu selesai");
        thread.join();
        System.out.println("program selesai");



    }
    @Test
    void threadInterruptCorrect()throws InterruptedException{
        Runnable runnable= () -> {
            for (var i =1 ; i < 10 ; i++) {
                System.out.println("runnable ke : " + i);

                if (Thread.interrupted())//ini kalo manual check interrupted
                    break;
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    return;
                }
            }
        };
        var thread=new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("menunggu selesai");
        thread.join();
        System.out.println("program selesai");
    }
    @Test
    void threadName() {
        Thread thread=new Thread(()->{
            System.out.println("run in thread : "+Thread.currentThread().getName());
        });

        thread.setName("wanda");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException {
        Thread thread=new Thread(()->{
            System.out.println(Thread.currentThread().getState());
            System.out.println("run in thread : "+Thread.currentThread().getName());
        });

        thread.setName("wanda");
        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState()    );
    }
}
