package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

public class CommunecationTest {

    private String massege;

    @Test
    void manual() throws InterruptedException {

        var thread1 = new Thread(() -> {
            while (massege == null) {
                //wait
            }
            System.out.println(massege);

        });

        var thread2=new Thread(()->{
            massege="ruswanda aja";
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();

    }
    @Test
    void waitNotify() throws InterruptedException {

        final var lock=new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(massege);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        var thread3= new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(massege);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        var thread2=new Thread(()->{
          synchronized (lock){
              massege="ruswanda aja";
              lock.notifyAll();
          }
        });

        thread1.start();
        thread3.start();
        thread2.start();

        thread1.join();
        thread3.join();
        thread2.join();

    }
}
