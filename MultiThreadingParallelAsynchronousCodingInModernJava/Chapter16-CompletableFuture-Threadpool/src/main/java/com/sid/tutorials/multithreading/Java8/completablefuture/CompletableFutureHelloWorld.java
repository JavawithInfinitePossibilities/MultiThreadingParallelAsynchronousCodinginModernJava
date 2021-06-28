package com.sid.tutorials.multithreading.Java8.completablefuture;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.delay;
import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.startTime;
import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.timeTaken;
import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.log;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sid.tutorials.multithreading.Java8.services.HelloWorldService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompletableFutureHelloWorld {
	private HelloWorldService hws;

	public CompletableFuture<String> helloWorld() {
		return CompletableFuture.supplyAsync(() -> {
			return hws.helloWorld();
		}).thenApply((result) -> {
			return result.toUpperCase();
		});

		// delay(2000);
	}

	public String helloWorld_approach1() {
		String hello = hws.hello();
		String world = hws.world();
		return hello + world;
	}

	public String helloworld_muliple_async_calls() {
		startTime(true);
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
		CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
		String helloWorld = hello.thenCombine(world, (helloResult, worldResult) -> {
			// first, second
			return helloResult + worldResult;
		}).thenApply(String::toUpperCase).join();
		timeTaken();
		return helloWorld;
	}

	public String helloworld_3_async_calls() {
		startTime(true);
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
			return hws.hello();
		});
		CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
			return hws.world();
		});
		CompletableFuture<String> hiComFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return "Hi CompletableFuture";
		});
		String helloWorld3HiComfuture = hello.thenCombine(world, (helloResult, worldResult) -> {
			return helloResult + worldResult;
		}).thenCombine(hiComFuture, (helloWorldResult, hiComFutureResult) -> {
			return helloWorldResult + hiComFutureResult;
		}).thenApply(String::toUpperCase).join();
		timeTaken();
		return helloWorld3HiComfuture;
	}

	public String helloworld_3_async_calls_custom_threadpool() {
		startTime(true);
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello(), executorService);
		CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world(), executorService);
		CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
			delay(1000);
			return " Hi CompletableFuture!";
		}, executorService);
		String hw = hello.thenCombine(world, (h, w) -> {
			log("thenCombine h/w");
			return h + w;
		}) // first, second
				.thenCombine(hiCompletableFuture, (previous, current) -> {
					log("thenCombine previous/current");
					return previous + current;
				}).thenApply(s -> {
					log("thenApply");
					return s.toUpperCase();
				}).join();
		timeTaken();
		return hw;
	}

	public CompletableFuture<String> helloWorld_thenCompose() {
		return CompletableFuture.supplyAsync(() -> hws.hello()).thenCompose((previous) -> hws.worldFuture(previous))
				.thenApply(String::toUpperCase)
		/* .join() */;
	}

	public static void main(String[] args) {
		HelloWorldService hws = new HelloWorldService();
		CompletableFuture.supplyAsync(hws::helloWorld).thenApply(String::toUpperCase).thenAccept((result) -> {
			log("Result is : " + result);
		}).join();
		log("Done!");
		// delay(2000);
	}
}
