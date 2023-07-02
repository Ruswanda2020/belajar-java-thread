package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueTest {
    @Test
    void arrayBlockingQueue() throws InterruptedException {

        final var queue =new ArrayBlockingQueue<String>(5);
        final var exexutor= Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            exexutor.execute(()->{
                try {
                    queue.put("data");
                    System.out.println("finish put data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exexutor.execute(()->{
              while (true){
                  try {
                      Thread.sleep(2000);
                      var value= queue.take();
                      System.out.println("recive data : "+value);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
        });
        exexutor.awaitTermination(1,TimeUnit.DAYS);

    }

    @Test
    void linkedBlockingQueue() throws InterruptedException {

        final var queue =new LinkedBlockingQueue<String>();
        final var exexutor= Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            exexutor.execute(()->{
                try {
                    queue.put("data");
                    System.out.println("finish put data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exexutor.execute(()->{
            while (true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("recive data : "+value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exexutor.awaitTermination(1,TimeUnit.DAYS);

    }
    @Test
    void priorityBlockingQueue() throws InterruptedException {

        final var queue =new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());
        final var exexutor= Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index=i;
            exexutor.execute(()->{
                queue.put(index);
                System.out.println("finish put data");
            });
        }
        exexutor.execute(()->{
            while (true){
                try {
                    Thread.sleep(2000);
                    var value= queue.take();
                    System.out.println("recive data : "+value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exexutor.awaitTermination(1,TimeUnit.DAYS);

    }
    @Test
    void delayBlockingQueue() throws InterruptedException {

        final var queue =new DelayQueue<ScheduledFuture<String>>();
        final var exexutor= Executors.newFixedThreadPool(20);
        final var executorScheduled=Executors.newScheduledThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            final var index=i;
            int finalI = i;
            exexutor.execute(()->{
                queue.put(executorScheduled.schedule(()->" Data " +index, finalI,TimeUnit.SECONDS));
            });
        }
        exexutor.execute(()->{
            while (true){
                try {
                    var value= queue.take();
                    System.out.println("recive data : "+value.get());
                    System.out.println("finish");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        exexutor.awaitTermination(1,TimeUnit.DAYS);



    }

    @Test
    void synchronousqueue() throws InterruptedException {

        final var queue = new SynchronousQueue<String>();
        final var exexutor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            exexutor.execute(() -> {
                try {
                    queue.put("Data-" + index);
                    System.out.println("finish put data " + index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        exexutor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take();
                    System.out.println("recive data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exexutor.awaitTermination(1,TimeUnit.DAYS);
    }
    @Test
    void blockingDeque() throws InterruptedException {

        final var queue = new LinkedBlockingDeque<String>();
        final var exexutor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
                try {
                    queue.putLast("Data-" + index);//kalo menggunakan putlast itu artinya lifo,dan ada juga method putfirs itu artinya fifo
                    System.out.println("finish put data " + index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }
        exexutor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.takeFirst();
                    System.out.println("recive data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exexutor.awaitTermination(1,TimeUnit.DAYS);
    }

    @Test
    void transferDeque() throws InterruptedException {

        final var queue = new LinkedTransferQueue<String>();
        final var exexutor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
          exexutor.execute(()->{
              try {
                  queue.transfer("Data-" + index);
                  System.out.println("finish put data " + index);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          });
        }
        exexutor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take();
                    System.out.println("recive data : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exexutor.awaitTermination(1,TimeUnit.DAYS);
    }

}
