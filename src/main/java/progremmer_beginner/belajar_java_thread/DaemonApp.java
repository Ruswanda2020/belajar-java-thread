package progremmer_beginner.belajar_java_thread;

public class DaemonApp {
    public static void main(String[] args) {
        var thread=new Thread(()->{
            try {
                Thread.sleep(3_000);
                System.out.println("run thread");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.setDaemon(true);
        thread.start();
    }
}
