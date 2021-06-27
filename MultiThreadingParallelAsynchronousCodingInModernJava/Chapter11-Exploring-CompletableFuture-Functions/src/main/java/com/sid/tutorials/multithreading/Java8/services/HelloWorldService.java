package com.sid.tutorials.multithreading.Java8.services;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.*;
import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;

import java.util.concurrent.CompletableFuture;

public class HelloWorldService {

	public String helloWorld() {
		delay(1000);
		log("inside helloWorld");
		return "hello world";
	}

	public String hello() {
		delay(1000);
		log("inside hello");
		return "hello";
	}

	public String world() {
		delay(1000);
		log("inside world");
		return " world!";
	}

	public CompletableFuture<String> worldFuture(String input) {
		return CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return input + " world!";
		});
	}

}
