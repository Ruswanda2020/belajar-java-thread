package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//note
//jika meruningnya ingin mengammbalikan value maka gunakan callable dan ruten value nya future
public class FutureTest {
    @Test
    void testFuture() throws ExecutionException, InterruptedException {
        var executor= Executors.newSingleThreadExecutor();
        Callable<String> callable =()->{
            Thread.sleep(5000);
            return "hai";
        };
       Future<String> future=executor.submit(callable);
        System.out.println("selesai future");

        while (!future.isDone()){
            System.out.println("waiting future");
            Thread.sleep(1000);
        }

        var value=future.get();
        System.out.println(value);
    }
    @Test
    void testFutureCancel() throws ExecutionException, InterruptedException {
        var executor= Executors.newSingleThreadExecutor();
        Callable<String> callable =()->{
            Thread.sleep(5000);
            return "hai";
        };
        Future<String> future=executor.submit(callable);//sederhananya future bisa mengunakan metohs future
        System.out.println("selesai future");
        Thread.sleep(2000);
        future.cancel(true);

        System.out.println(future.isCancelled());
        var value=future.get();
        System.out.println(value);
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        var executor=Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1,11).mapToObj(operand->(Callable<String>)()->{
            Thread.sleep(operand*500L);
            return String.valueOf(operand);
        }).collect(Collectors.toList());

        var futures=executor.invokeAll(callables);//kalo ingin mengeksekusinya menggunakan metod invokAll & menungu semua futurnya
        for (Future<String> stringFuture : futures){
            System.out.println(stringFuture.get());
        }
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        var executor=Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1,11).mapToObj(operand->(Callable<String>)()->{
            Thread.sleep(operand*500L);
            return String.valueOf(operand);
        }).collect(Collectors.toList());

        var value=executor.invokeAny(callables);//kalo ingin mengeksukusi semuanyah dan mengambil yang tercepat gunakan metod invokAny
        System.out.println(value);
    }
}
