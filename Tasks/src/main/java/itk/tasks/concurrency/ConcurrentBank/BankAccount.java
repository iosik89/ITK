package itk.tasks.concurrency.ConcurrentBank;

import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private final long id;
    private long balance;

    public BankAccount(long initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("initialBalance must be positive!");
        }
        this.id = ID_GENERATOR.getAndIncrement();
        this.balance = initialBalance;
    }

    public long getId() {
        return id;
    }

    public synchronized void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("initialBalance must be positive!");
        }
        balance += amount;
    }

    public synchronized void withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("initialBalance must be positive!");
        }
        if (balance < amount) {
            throw new IllegalStateException("Insufficient funds!");
        }
        balance -= amount;
    }

    public synchronized long getBalance() {
        return balance;
    }
}



