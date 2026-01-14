package itk.tasks.concurrency.blockinqqueue;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T>  {

    private final int size;
    private final Queue<T> queue = new LinkedList<>();

    public BlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void enqueue(T t) throws InterruptedException {
        while (queue.size()==size){
            wait();
        }
        queue.add(t);
        notify();
    }

    public synchronized T dequeue() throws InterruptedException {
        if (queue.isEmpty()){
            wait();
        }
        T item = queue.poll();
        notify();
        return item;
    }

    public synchronized int size(){
        return queue.size();
    }

}
