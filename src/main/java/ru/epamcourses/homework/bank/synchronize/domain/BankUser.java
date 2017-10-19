package ru.epamcourses.homework.bank.synchronize.domain;

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
                synchronized (bank) {
                    if (bank.hasMoney(moneyWithdraw)) {
                        bank.getMoney(moneyWithdraw);
                    } else break;
                }
            }
        } catch (NoMoneyException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + " thread finished work.");
    }
}
