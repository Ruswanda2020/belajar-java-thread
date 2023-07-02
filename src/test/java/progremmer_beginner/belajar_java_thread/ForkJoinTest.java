package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinTest {


    public static class ForkJoinSimple extends RecursiveAction{
        private List<Integer> integers;

        public ForkJoinSimple(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected void compute() {
            if (integers.size() <= 10){
                //eksekusi
                doActio();
            }else {
                //fork
                forkCompute();
            }
        }

        private void forkCompute() {
            var middle= integers.size()/2;
            List<Integer> integers1=integers.subList(0,middle);
            List<Integer> integers2=integers.subList(middle,integers.size());

            ForkJoinSimple task1 =new ForkJoinSimple(integers1);
            ForkJoinSimple task2 =new ForkJoinSimple(integers2);

            ForkJoinTask.invokeAll(task1,task2);

        }

        private void doActio() {
            integers.forEach(integer -> System.out.println(Thread.currentThread().getName()+":"+integer));
        }
    }
    @Test
    void testRecurisiveAction() throws InterruptedException {
        var pool=ForkJoinPool.commonPool();//menggunakan cpu local computer
        //var pool= new ForkJoinPool(5);
        List<Integer> integers=IntStream.range(0,500).boxed().collect(Collectors.toList());
        var task=new ForkJoinSimple(integers);
        pool.execute(task);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);

    }

    public static class TotalForkJoinTask extends RecursiveTask<Long>{
        
        List<Integer> integers;

        public TotalForkJoinTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected Long compute() {
            if (integers.size() <= 10) {
                //eksekusi
                return doCompute();
            }else {
                return forkCompute();
            }
        }

        private Long forkCompute() {
            var midlle =this.integers.size()/2;
            List<Integer> integers1=this.integers.subList(0,midlle);
            List<Integer> integers2=this.integers.subList(midlle,integers.size());

            TotalForkJoinTask task1=new TotalForkJoinTask(integers1);
            TotalForkJoinTask task2=new TotalForkJoinTask(integers2);

            ForkJoinTask.invokeAll(task1,task2);

            return task1.join()+task2.join();
        }
        private Long doCompute() {
            return integers.stream().mapToLong(value -> value)
                    .peek(value -> {System.out.println(Thread.currentThread().getName()+" : "+value);
                    }).sum();
        }
    }
    @Test
    void recursiveTask() throws ExecutionException, InterruptedException {
        var pool=ForkJoinPool.commonPool();
        List<Integer> integers=IntStream.range(0,500).boxed().collect(Collectors.toList());

        var task=new TotalForkJoinTask(integers);
        var submit=pool.submit(task);

        Long along=submit.get();
        System.out.println(along);

       // long sum=integers.stream().mapToLong(value -> value).sum();
       // System.out.println(sum);

        pool.shutdown();
        pool.awaitTermination(1,TimeUnit.DAYS);


    }
}



