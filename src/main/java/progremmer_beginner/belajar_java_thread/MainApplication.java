package progremmer_beginner.belajar_java_thread;

public class MainApplication {

    public static void main(String[] args) {
        var name=Thread.currentThread().getName();
        System.out.println(name);
    }
}