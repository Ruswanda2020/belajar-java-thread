package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class StreamTest {
    @Test
    void parallel() {
        var stream=IntStream.range(0,100).boxed();
        stream.parallel().forEach(integer -> {
            System.out.println(Thread.currentThread().getName()+" "+integer);
        });
    }

    @Test
    void cutomPool() {
        //var pool = ForkJoinPool.commonPool();
        var pool=new ForkJoinPool(1);
        var task =pool.submit(()->{
            var stream=IntStream.range(0,100).boxed();
            stream.parallel().forEach(integer -> {
                System.out.println(Thread.currentThread().getName()+" "+integer);
            });
        });
    }
}
