package itk.tasks.concurrency.ConcurrentBank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {

    private final Map<Long, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(long initialBalance) {
        BankAccount account = new BankAccount(initialBalance);
        accounts.put(account.getId(), account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, long amount) {
        if (from == to) return;

        BankAccount firstLock = from.getId() < to.getId() ? from : to;
        BankAccount secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    public long getTotalBalance() {
        long total = 0;
        for (BankAccount account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }
}
