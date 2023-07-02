package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentMapTest {
    @Test
    void concurrentMap() throws InterruptedException {
        final var countdown=new CountDownLatch(100);
        final var map = new ConcurrentHashMap<Integer,String>();
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            final var index=i;
            executor.execute(()->{
                try {
                    Thread.sleep(2000);
                    map.putIfAbsent(index," Data- " +index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    countdown.countDown();
                }
            });
        }

        executor.execute(()->{
            try {
                countdown.await();
                map.forEach((Integer,String)-> System.out.println(Integer + ":" + String));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void testColletion() {
        List<String> list=List.of("wanda","angga","ridwan");
        List<String> synchronizedList = Collections.synchronizedList(list);
        System.out.println(synchronizedList);
    }


}
