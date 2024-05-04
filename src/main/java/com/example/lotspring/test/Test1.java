package com.example.lotspring.test;


public class Test1 {

    // 设置共享变量
    private static int state = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
             try {
                 for (int i = 0; i < 100; i++) {
                     synchronized (lock) {
                         while (state % 3 != 0) {
                             lock.wait();
                         }
                         System.out.println("A");
                         state++;
                         lock.notifyAll();
                     }
                 }
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         });

        Thread threadB = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (lock) {
                        while (state % 3 != 1) {
                            lock.wait();
                        }
                        System.out.println("B");
                        state++;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    synchronized (lock) {
                        while (state % 3 != 2) {
                            lock.wait();
                        }
                        System.out.println("C");
                        state++;
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }

}
