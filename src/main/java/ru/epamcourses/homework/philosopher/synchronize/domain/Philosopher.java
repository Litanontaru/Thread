package ru.epamcourses.homework.philosopher.synchronize.domain;

import ru.epamcourses.homework.philosopher.Fork;

import java.time.LocalTime;

public class Philosopher implements Runnable {

    private final Fork leftFork;
    private final Fork rightFork;
    private final String name;

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
                synchronized (leftFork) {
                    synchronized (rightFork) {
                        eating();
                    }
                }
                thinking();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}