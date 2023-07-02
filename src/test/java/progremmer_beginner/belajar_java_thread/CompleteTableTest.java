package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompleteTableTest {

    private ExecutorService executorService= Executors.newFixedThreadPool(10);
    private Random random=new Random();
    public CompletableFuture<String> getValue(){
        CompletableFuture<String> future =new CompletableFuture<>();

        executorService.execute(()->{
            try {
                Thread.sleep(2000);
                future.complete("ruswanda dirgantara dot com");
            } catch (InterruptedException e) {
               future.completeExceptionally(e);
            }
        });

        return future;
    }

    @Test
    void create() throws ExecutionException, InterruptedException {
       Future<String> future = getValue();
        System.out.println(future.get());
    }

    public void excecute(CompletableFuture<String>future,String value){
        executorService.execute(()->{
            try {
                Thread.sleep(1000 + random.nextInt(5000));
                future.complete(value);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
    }
    public Future<String> getFastest(){
        CompletableFuture<String> future =new CompletableFuture<>();

        excecute(future,"thread 1");
        excecute(future,"thread 2");
        excecute(future,"thread 3");//pengunaan future manual tanpa mengunakan callabale seperti contoh di atas

        return future;
    }
    @Test
    void testFastest() throws ExecutionException, InterruptedException {
        System.out.println(getFastest().get());
    }

    @Test
    void completionStage() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future=getValue();

        CompletableFuture<String[]>future1 = future.thenApply(String::toUpperCase).
                thenApply(value -> value.split(" "));

        String[] strings=future1.get();
        for (var result: strings) {
            System.out.println(result);
        }

        //dengan menggunakan completionStage kita tidak harus nunggu dulu data nya ada dulu baru di
        // oprasikan kita bisa langsung mengoprasikan
    }
}