package progremmer_beginner.belajar_java_thread;

import org.junit.jupiter.api.Test;

public class DeadLockTest {
        @Test
        void testDeadLock() throws InterruptedException {

                var balance1=new Balance(1_000_000L);
                var balance2=new Balance(1_000_000L);

                var thread1=new Thread(()->{
                        try {
                                Balance.transfer(balance1,balance2,50000L);

                        } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                        }
                });

                var thread2=new Thread(()->{
                        try {
                                Balance.transfer(balance2,balance1,500000L);

                        } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                        }
                });

                thread1.start();
                thread2.start();

                thread1.join();
                thread2.join();



                System.out.println("Balance 1 : "+balance1.getValue());
                System.out.println("Balance 2 : "+balance2.getValue());

        }
}
