package ru.epamcourses.homework.philosopher.concurrent.domain;

import ru.epamcourses.homework.philosopher.Fork;

import java.time.LocalTime;

public class Philosopher implements Runnable {

    private final Fork leftFork;
    private final Fork rightFork;
    private final String name;

    //Нарушение JCC - статические поля должны быть объявлены выше нестатических
    private static LocalTime localTime = LocalTime.now().plusSeconds(5);

    public Philosopher(Fork leftFork, Fork rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
    }

    private void eating() {
        System.out.println("Philosopher " + name + " eating");
    }

    private void thinking() throws InterruptedException {
        System.out.println("Philosopher " + name + " thinking");
        Thread.sleep(100);
    }

    private int timeOfWork() {
        return localTime.getSecond() - LocalTime.now().getSecond();
    }

    @Override
    public void run() {
        try {
            while (timeOfWork() != 0) {
                leftFork.lock();
                rightFork.lock();
                eating();
                //Существует строгая практика делать unlock в секции finally,
                //иначе при выпадении Error или Exception lock не будет отпушени
                //и система войдёт в неконсистентное состояние
                leftFork.unlock();
                rightFork.unlock();
                thinking();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
