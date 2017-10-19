package ru.epamcourses.homework.bank.concurrent.test;

import ru.epamcourses.homework.bank.concurrent.domain.Bank;
import ru.epamcourses.homework.bank.concurrent.domain.BankUser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank(3010);

        Runnable client1 = new BankUser("A", bank, 30);
        Runnable client2 = new BankUser("B", bank, 10);
        Runnable client3 = new BankUser("C", bank, 30);
        Runnable client4 = new BankUser("D", bank, 20);
        Runnable client5 = new BankUser("E", bank, 40);

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(client1);
        executor.execute(client2);
        executor.execute(client3);
        executor.execute(client4);
        executor.execute(client5);
        executor.shutdown();
    }
}
