package com.honghao.concurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    private List<T> queue;
    private int count;
    private final int capacity;
    private Lock takeLock;
    private Lock putLock;
    private Condition notEmpty;
    private Condition notFull;

    public MyBlockingQueue() {
        queue = new ArrayList<T>();
        takeLock = new ReentrantLock();
        putLock = new ReentrantLock();
        notEmpty = takeLock.newCondition();
        notFull = putLock.newCondition();
        this.capacity = 8;
    }

    public MyBlockingQueue(int capacity) {
        queue = new ArrayList<T>();
        takeLock = new ReentrantLock();
        putLock = new ReentrantLock();
        notEmpty = takeLock.newCondition();
        notFull = putLock.newCondition();
        this.capacity = capacity;
    }

    public void put(T t) throws InterruptedException {
        try {
            putLock.lock();
            while (count == capacity - 1)
                notFull.await();
            System.out.println("put " + t);
            queue.add(t);
            count ++;
            takeLock.lock();
            notEmpty.signal();
        } finally {
            takeLock.unlock();
            putLock.unlock();
        }

    }

    public T take() throws InterruptedException {
        T t;
        try {
            takeLock.lock();
            while (count == 0)
                notEmpty.await();
            t = queue.get(0);
            System.out.println("take " + t);
            queue.remove(0);
            count --;
            putLock.lock();
            notFull.signal();
        } finally {
            putLock.unlock();
            takeLock.unlock();
        }

        return t;
    }
}
