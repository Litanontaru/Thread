package ru.epamcourses.homework.bank.synchronize.domain;

import ru.epamcourses.homework.bank.exception.NoMoneyException;

public class Bank {
    private int moneyAmount;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
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
