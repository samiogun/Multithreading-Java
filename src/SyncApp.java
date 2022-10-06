import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Printer {
    /*synchronized*/ void printDocuments(int numOfCopies, String docName) {
        for (int i = 1; i <= numOfCopies; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">> Printing " + docName + " " + i);
        }
    }
}

class MyThread extends Thread {

    private final Printer pRef;

    MyThread(Printer p) {
        this.pRef = p;
    }

    @Override
    public void run() {
        //synchronized (pRef) {
        System.out.println("MyThread pRef" + pRef + ", Thread name : " + Thread.currentThread().getName() + " Start Date : " + Instant.now());
        pRef.printDocuments(10, "MyProfile.pdf");
        System.out.println("MyThread pRef" + pRef + ", Thread name : " + Thread.currentThread().getName() + " Finish Date : " + Instant.now());
        //}

    }
}

class YourThread extends Thread {

    private final Printer pRef;

    YourThread(Printer p) {
        this.pRef = p;
    }

    @Override
    public void run() {

        //synchronized (pRef) {
        System.out.println("YourThread pRef" + pRef + ", Thread name : " + Thread.currentThread().getName() + " Start Date : " + Instant.now());
        pRef.printDocuments(10, "YourProfile.pdf");
        System.out.println("YourThread pRef" + pRef + ", Thread name : " + Thread.currentThread().getName() + " Finish Date : " + Instant.now());
        //}

    }
}

public class SyncApp {
    public static void main(String[] args) {
        System.out.println("========Application started.=========");

        Printer printer = new Printer();
//        printer.printDocuments(10, "OgunProfile.pdf");

        MyThread myThread = new MyThread(printer);
        YourThread yourThread = new YourThread(printer);

//        myThread.start();
        /*try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        yourThread.start();

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
//        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

//        executorService.submit(myThread);
//        executorService.submit(yourThread);
//        cachedExecutor.submit(yourThread);
//        cachedExecutor.submit(myThread);
//        scheduledExecutor.schedule(yourThread,5, TimeUnit.SECONDS); // 5 saniye sonra bir kereliğine Taskı yerine getirir.
//        scheduledExecutor.schedule(myThread,5, TimeUnit.SECONDS); // 5 saniye sonra bir kereliğine Taskı yerine getirir.
//        scheduledExecutor.scheduleAtFixedRate(myThread, 0, 5, TimeUnit.SECONDS); // Her 5 saniyede bir Taskı yerine getirir. Taskın tamamlanmasını beklemez. 5 saniye içinde Task tamamlanmazsa bile aynı taskı başka Thread oluşturarak gerçekleştirir.
//        scheduledExecutor.scheduleAtFixedRate(yourThread, 0, 5, TimeUnit.SECONDS); // Her 5 saniyede bir Taskı yerine getirir. Taskın tamamlanmasını beklemez. 5 saniye içinde Task tamamlanmazsa bile aynı taskı başka Thread oluşturarak gerçekleştirir.
//        scheduledExecutor.scheduleWithFixedDelay(yourThread,0,3,TimeUnit.SECONDS);
//        scheduledExecutor.scheduleWithFixedDelay(yourThread, 0, 3, TimeUnit.SECONDS);
        singleThreadExecutor.submit(myThread);
        singleThreadExecutor.submit(yourThread);


        System.out.println("Available Processors : " + Runtime.getRuntime().availableProcessors());

//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {        // Scheduled threadler için main Thread'in durmasını sağlama, shutdown metoduna gitmeden önce.
//            e.printStackTrace();
//        }

//        executorService.shutdown();
//        cachedExecutor.shutdown();
        singleThreadExecutor.shutdown();


        System.out.println("========Application finished.=========");
    }
}
