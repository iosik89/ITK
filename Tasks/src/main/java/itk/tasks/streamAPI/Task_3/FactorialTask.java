package itk.tasks.streamAPI.Task_3;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private int a;
    private static final int THRESHOLD = 3;
    private int start;
    private int end;

    public FactorialTask(int n) {
        this(1, n);
    }

    private FactorialTask(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        if ((end - start) <= THRESHOLD) {
            long res = 1L;
            for (int i = start; i <= end; i++) {
                res *= i;
            }
            return res;
        }

        int mid = (start+end)/2;

        FactorialTask leftTask = new FactorialTask(start,mid);
        FactorialTask rightTask = new FactorialTask(mid+1,end);

        leftTask.fork();

        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();

        return leftResult * rightResult;
    }
}
