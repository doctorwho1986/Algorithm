package com.github.interview.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceP {

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8,9,0,10,11,12,13};
		int nThreads = 4;
		int count = a.length / nThreads + 1;
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		int start = 0;
		int setp = count;
		while (nThreads > 0) {
			nThreads--;
			threadPool.execute(new MyRunable(a, start, start + setp,a.length));
			start += count;
		}
		
		threadPool.shutdown();
	}

}

class MyRunable implements Runnable{
	private int[] a;
	private int start;
	private int end;
	private int max ;
	
	public MyRunable(int[] a, int start, int end,int max) {
		this.a = a;
		this.start =start;
		this.end = end;
		this.max = max;
	}
	@Override
	public void run() {
		while (start != end && start < max) {
			System.out.println(Thread.currentThread().getName() + ": " + start);
			start ++;
		}
		
	}
	
}
