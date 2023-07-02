package progremmer_beginner.belajar_java_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
    private Long counter=0L;

    final private Lock lock= new ReentrantLock();

    public void increment(){
        try {
            lock.lock();
            counter++;
        }finally {
            lock.unlock();
        }
    }

    public Long getConter(){
        return counter;
    }
}
