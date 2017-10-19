package ru.epamcourses.homework.bank.concurrent.domain;

import ru.epamcourses.homework.bank.exception.NoMoneyException;

import java.util.concurrent.Semaphore;

public class Bank {
    private int moneyAmount;
    private final Semaphore semaphore;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
        semaphore = new Semaphore(1, true);
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void getMoney(int amount) throws NoMoneyException {
        if (moneyAmount < amount)
            throw new NoMoneyException("The bank does not have any money");
        moneyAmount -= amount;
        System.out.println("The bank has " + moneyAmount + " money.");
    }

    public boolean hasMoney(int moneyWithdraw) {
        return moneyAmount - moneyWithdraw >= 0;
    }
}
