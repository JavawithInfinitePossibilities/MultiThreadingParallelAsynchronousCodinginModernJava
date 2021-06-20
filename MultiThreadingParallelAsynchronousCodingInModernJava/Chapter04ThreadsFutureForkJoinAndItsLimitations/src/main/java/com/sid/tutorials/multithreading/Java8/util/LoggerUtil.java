package com.sid.tutorials.multithreading.Java8.util;

public class LoggerUtil {

	public static void log(String message) {
		System.out.println("[" + Thread.currentThread().getName() + "] - " + message);
	}
}
