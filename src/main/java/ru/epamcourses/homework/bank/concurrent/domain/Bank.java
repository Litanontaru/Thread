package ru.epamcourses.homework.bank.concurrent.domain;

import ru.epamcourses.homework.bank.exception.NoMoneyException;

import java.util.concurrent.Semaphore;

public class Bank {
    //В будущем стоит изучить необходимость использования volatile для подобных переменных
    //В реальном продакшене без volatile это не будет работать
    private int moneyAmount;
    //Рекомендуется все final поля (aka. конфигурацию-сервисы) объявлять выше не final,
    //отделяя таким образом поля состояния от полей от конфигурации
    private final Semaphore semaphore;

    public Bank(int moneyAmount) {
        this.moneyAmount = moneyAmount;
        semaphore = new Semaphore(1, true);
    }

    //Этот метод нарушает принцип "Open/Closed" из SOLID
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
