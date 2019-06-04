package com.honghao.concurrent;

public class TakeThread extends Thread {
    private MyBlockingQueue<Integer> blockingQueue;

    public TakeThread(MyBlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        try {
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
