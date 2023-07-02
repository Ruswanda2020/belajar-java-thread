package progremmer_beginner.belajar_java_thread;

public class SynchronizationCounter {
    private Long counter=0L;

    public void increment(){
        //afafdsfdsfds
        synchronized (this){
            counter++;
        }
        //fwkfnakfasfsa
    }

    public Long getConter(){
        return counter;
    }

}
