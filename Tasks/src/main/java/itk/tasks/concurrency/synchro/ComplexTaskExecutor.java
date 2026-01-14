package itk.tasks.concurrency.synchro;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplexTaskExecutor {

    int amountTasks;

    public ComplexTaskExecutor(int amountTasks) {
        this.amountTasks = amountTasks;
    }

    public synchronized void executeTasks(int numberOfTasks) {
        if (numberOfTasks != amountTasks) {
            System.out.println("Ошибка: Кол-во задач должно совпадать с кол-ом потоков!");
            return;
        }
        AtomicInteger result = new AtomicInteger(0);
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("Объединенный результат CompleteTask: " + result.get());
        });
        try (ExecutorService service = Executors.newFixedThreadPool(numberOfTasks)) {
            for (int i = 0; i < amountTasks; i++) {
                int taskId = i;
                service.submit(() -> {
                    //Выполняем задачу и получаем результат
                    result.addAndGet(new ComplexTask(taskId).execute().getResult());
                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }
}

