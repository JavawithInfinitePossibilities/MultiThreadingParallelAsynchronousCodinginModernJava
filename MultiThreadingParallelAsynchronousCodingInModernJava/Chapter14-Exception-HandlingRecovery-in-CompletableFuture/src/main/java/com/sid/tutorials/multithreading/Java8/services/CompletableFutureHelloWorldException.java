package com.sid.tutorials.multithreading.Java8.services;

import java.util.concurrent.CompletableFuture;

import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;
import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.*;

public class CompletableFutureHelloWorldException {

	private HelloWorldService hws;

	public CompletableFutureHelloWorldException(HelloWorldService hws) {
		this.hws = hws;
	}

	public String helloworld_3_async_calls_handle() {
		startTime(true);
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
		CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
		CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Hi CompletableFuture!";
		});
		String hw = hello.handle((res, e) -> {
			log("Exception is :" + e.getMessage());
			return "";
		}).thenCombine(world, (h, w) -> h + w) // " world!"
				.thenCombine(hiCompletableFuture, (previous, current) -> previous + current)
				// " world! Hi CompletableFuture!"
				.thenApply(String::toUpperCase) // " WORLD! HI COMPLETABLEFUTURE!"
				.join(); // " WORLD! HI COMPLETABLEFUTURE!"

		timeTaken();
		return hw;
	}
}
