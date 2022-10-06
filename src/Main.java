import java.sql.Time;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

//        for (int i = 0; i < 5; i++) {
//            MultiThread multiThread = new MultiThread(i);
//            multiThread.start();
//        }

//        for (int i = 0; i < 5; i++) {
//            MultiThreadRunnable multiThreadRunnable = new MultiThreadRunnable(i);
//            Thread newThread = new Thread(multiThreadRunnable);
//            newThread.start();
//        }

        Runnable runnable = () -> {

            for (int i = 1; i <= 5; i++) {
                System.out.println(i + " from Thread : #" + Thread.currentThread().getId() + " - " + new Date().toInstant());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread newThread = new Thread(runnable);
            newThread.start();
        }


    }
}
