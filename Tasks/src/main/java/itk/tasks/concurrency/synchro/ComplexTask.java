package itk.tasks.concurrency.synchro;

import java.util.concurrent.ThreadLocalRandom;

public class ComplexTask {
    private final int id;
    private int result;

    public ComplexTask(int id) {
        this.id = id;
    }

    public ComplexTask execute() {
        result = ThreadLocalRandom.current().nextInt(0, id+1);
        System.out.printf("Поток %s выполняет задачу %d...%n",
                Thread.currentThread().getName(), id);
        return this;
    }

    public int getResult() {
        return result;
    }
}

