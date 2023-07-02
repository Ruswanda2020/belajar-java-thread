package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class ReactiveStream {

    @Test
    void publish() throws InterruptedException {

        var executor= Executors.newFixedThreadPool(10);
        SubmissionPublisher<String> publisher=new SubmissionPublisher<>();

        var subscriber1=new Printsubscriber("A",500L);
        var subscriber2 =new Printsubscriber("B",2000L);

        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        executor.execute(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(2000);
                    publisher.submit("wanda - "+i);
                    System.out.println(Thread.currentThread().getName()+" send wanda : "+i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(100*1000);
    }

    @Test
    void buffer() throws InterruptedException {
        var executor= Executors.newFixedThreadPool(10);
        SubmissionPublisher<String> publisher=new SubmissionPublisher<>(Executors.newWorkStealingPool(10),10);

        var subscriber1=new Printsubscriber("A",500L);
        var subscriber2 =new Printsubscriber("B",2000L);

        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        executor.execute(()->{
            for (int i = 0; i < 1000; i++) {
                publisher.submit("wanda - " + i);
                System.out.println(Thread.currentThread().getName() + " send wanda : " + i);
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(100*1000);
    }

    public static class Printsubscriber implements Flow.Subscriber<String>{

        private Flow.Subscription subscription;
        private String name;
        private Long sleep;

        public Printsubscriber(String name, Long sleep) {
            this.name = name;
            this.sleep = sleep;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription=subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            try {
                Thread.sleep(sleep);
                System.out.println(Thread.currentThread().getName()+" : " +name + " : " + item);
                this.subscription.request(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.getMessage();
        }

        @Override
        public void onComplete() {
            System.out.println(Thread.currentThread().getName()+" : Done");
        }
    }


    public static class HalloProcessor extends SubmissionPublisher<String> implements Flow.Processor<String,String>{

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription=subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            var valu="hallo "+item;
            submit(valu);
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.getMessage();
        }

        @Override
        public void onComplete() {
            close();
        }
    }

    @Test
    void Processor() throws InterruptedException {
        SubmissionPublisher<String> publisher=new SubmissionPublisher<>();

        var processor =new HalloProcessor();
        publisher.subscribe(processor);

        var subscribe=new Printsubscriber("A",1000L);
        processor.subscribe(subscribe);

        var executor=Executors.newFixedThreadPool(10);
        executor.execute(()->{
            for (int i = 0; i < 100; i++) {
                publisher.submit("wanda - " + i);
                System.out.println(Thread.currentThread().getName() + " send wanda : " + i);
            }
        });

        executor.shutdown();
        executor.awaitTermination(1,TimeUnit.DAYS);

        Thread.sleep(100*1000);

    }
}
