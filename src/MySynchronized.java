import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySynchronized {

    public static void main(String[] args) throws InterruptedException {
        int i;
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input i: ");
        i = sc.nextInt();
        System.out.println();

        Task task = new Task(i);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(task);
        executorService.shutdown();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.execute(task);
        executorService.shutdown();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        executorService3.execute(task);
        executorService.shutdown();
        Thread.sleep(1000);
        System.out.println("Total = " + task.getCount());

    }
}

class Task implements Runnable {
    private static int count;

    public static synchronized void count() {
        count++;
    }

    int x;

    public Task(int a) {
        x = a;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= x; i++) {
            System.out.println(
                    Thread.currentThread().getName() + ": " + i);
            count();
        }
    }

}