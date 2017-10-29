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
                    } else break; //нарушение JCC - break; на отдельной строке, желательно в { }
                }
            }
        } catch (NoMoneyException e) {
            System.out.println(e.getMessage());
        }
        //Confusing log. На самом деле это не thread закончил работу.
        //У тебя здесь Runnable и этому классу не известно - он один работает в потоке или нет.
        System.out.println(name + " thread finished work.");
    }
}
