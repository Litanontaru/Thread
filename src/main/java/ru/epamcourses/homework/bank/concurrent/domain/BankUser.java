package ru.epamcourses.homework.bank.concurrent.domain;

import ru.epamcourses.homework.bank.exception.NoMoneyException;

public class BankUser implements Runnable {
    private final Bank bank;
    private String name;
    private int moneyWithdraw;

    public BankUser(String name, Bank bank, int moneyWithdraw) {
        this.name = name;
        this.bank = bank;
        this.moneyWithdraw = moneyWithdraw;
    }

    @Override
    public void run() {
        try {
            while (true) {
                bank.getSemaphore().acquire();
                if (bank.hasMoney(moneyWithdraw)) {
                    bank.getMoney(moneyWithdraw);
                } else {
                    //обычно такие операции, как release делают в секции finally, чтобы гарантированно отпускать
                    bank.getSemaphore().release();
                    break;
                }
                bank.getSemaphore().release();
            }
        } catch (NoMoneyException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + " thread finished work");
    }
}
