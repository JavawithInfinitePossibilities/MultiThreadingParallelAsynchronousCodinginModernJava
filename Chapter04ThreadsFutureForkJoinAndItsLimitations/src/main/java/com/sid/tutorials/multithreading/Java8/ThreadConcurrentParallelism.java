/**
 * 
 */
package com.sid.tutorials.multithreading.Java8;

/**
 * @author Lenovo
 *
 */
public class ThreadConcurrentParallelism {
	public static String result = "";

	public static void hello() throws InterruptedException {
		Thread.sleep(5000);
		result = result.concat("Hello");
	}

	public static void world() throws InterruptedException {
		Thread.sleep(6000);
		result = result.concat(" World");
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread th1 = new Thread(() -> {
			try {
				hello();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread th2 = new Thread(() -> {
			try {
				world();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		th1.start();
		th2.start();

		th1.join();
		th2.join();
		System.out.println(result);
	}

}
