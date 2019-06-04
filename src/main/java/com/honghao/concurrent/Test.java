package com.honghao.concurrent;

public class Test {
    public static void main(String[] args) {
        MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<Integer>(2);
        for (int i = 0; i < 10; i ++) {
            new PutThread(blockingQueue, i).start();
        }

        for (int i = 0; i < 10; i ++) {
            new TakeThread(blockingQueue).start();
        }
    }
}
