package com.github.mstepan.template;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class AppMain {

    public static void main(String[] args) {

        final AtomicInteger threadsCount = new AtomicInteger(0);

        try {
            while (true) {
                Thread.ofPlatform()
                        .start(
                                () -> {
                                    int counter = threadsCount.incrementAndGet();

                                    if (counter % 10_000 == 0) {
                                        System.out.printf("Counter: %d%n", threadsCount.get());
                                    }

                                    LockSupport.park();
                                });
            }
        } catch (OutOfMemoryError ex) {
            System.out.printf("Created threads count: %d%n", threadsCount.get());
        }
    }
}
