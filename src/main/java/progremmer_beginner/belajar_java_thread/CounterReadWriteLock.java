package progremmer_beginner.belajar_java_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {
    private Long counter=0L;

    final private ReadWriteLock lock= new ReentrantReadWriteLock();

    public void increment(){
        try {
            lock.writeLock().lock();
            counter++;
        }finally {
            lock.writeLock().unlock();
        }
    }

    public Long getConter(){
       try {
           lock.readLock().lock();
           return counter;

       }finally {
           lock.readLock().unlock();
       }
    }
}
