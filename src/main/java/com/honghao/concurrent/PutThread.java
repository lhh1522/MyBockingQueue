package com.honghao.concurrent;

public class PutThread extends Thread {
    private MyBlockingQueue<Integer> blockingQueue;
    private int i;

    public PutThread(MyBlockingQueue<Integer> blockingQueue, int i) {
        this.blockingQueue = blockingQueue;
        this.i = i;
    }

    public void run() {
        try {
            blockingQueue.put(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
