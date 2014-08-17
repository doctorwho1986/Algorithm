package com.github.interview.thread;

import java.util.concurrent.TimeUnit;

public class ProducerConsumer {

	public static void main(String[] args) {
		Store store = new Store(8);
		new Producer(store).start();
		new Producer(store).start();
		new Consumer(store).start();
		new Consumer(store).start();

	}

}

class Store{
	private int maxSize;
	private int count = 0;
	Store(int maxSize){
		this.maxSize = maxSize;
		this.count = 0;
	}
	
	public synchronized void put() {
		if (count >= maxSize) {
			System.err.println(Thread.currentThread().getName() + "full");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.count ++;
		System.err.println(Thread.currentThread().getName() + "put" + this.count);
		this.notifyAll();
		
	}
	
	public synchronized void get() {
		if (count <=0) {
			System.err.println(Thread.currentThread().getName() + "waiting");
			try {
				this.wait();
				
			} catch (InterruptedException e) {
			}
		}
		
		
		System.err.println(Thread.currentThread().getName() + "get" + this.count);
		this.count--;
		this.notifyAll();
	}
}


class Producer extends Thread{
	private Store store;
	public Producer(Store store) {
		this.store = store;
	}
	@Override
	public void run() {
		while (true) {
			store.put();
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


class Consumer extends Thread{
	private Store store;
	public Consumer(Store store){
		this.store = store;
	}
	
	@Override
	public void run(){
		while (true) {
			store.get();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
