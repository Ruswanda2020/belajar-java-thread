package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    @Test
    void testLock() throws InterruptedException {
        var counter=new CounterLock();
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
    @Test
    void testReadWriteLock() throws InterruptedException {
        var counter=new CounterReadWriteLock();
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
     String massage;
    @Test
    void condition() throws InterruptedException {
        var lock=new ReentrantLock();
        var condition =lock.newCondition();

        var thread1=new Thread(()->{
            try {
                lock.lock();
                condition.await();
                System.out.println(massage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
               lock.unlock();
            }
        });

        var thread3=new Thread(()->{
            try {
                lock.lock();
                condition.await();
                System.out.println(massage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        var thread2=new Thread(()->{
            try {
                lock.lock();
                condition.signalAll();
                Thread.sleep(2000);
                massage="hallo wanda";
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
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
