package com.example.jpamybatisplusdemo.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {

    public synchronized static void main(String[] args) {
        Thread thread = new Thread();
        Lock lock = new ReentrantLock();
        lock.lock();
        System.out.println("Dddd");

    }
}
