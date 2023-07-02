package progremmer_beginner.belajar_java_thread;

import java.util.concurrent.atomic.AtomicLong;

public class CounterAutomic {
    private AtomicLong counter=new AtomicLong(0L);

    public void increment(){
        counter.incrementAndGet();
    }

    public Long getConter(){
        return counter.get();
    }

    //maasih banyak lagi method2 lainya yang bisa di gunakan misal untuk integer,boolean dll.
    //di sesuaikan saja dengan kebutuhuan

}
