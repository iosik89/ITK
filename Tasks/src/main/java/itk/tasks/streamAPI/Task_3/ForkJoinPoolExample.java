package itk.tasks.streamAPI.Task_3;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            FactorialTask factorialTask = new FactorialTask(n);


            long result = forkJoinPool.invoke(factorialTask);

            System.out.println("Факториал " + n + "! = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

