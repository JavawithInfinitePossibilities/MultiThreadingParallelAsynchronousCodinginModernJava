package com.sid.tutorials.multithreading.Java8.services;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.*;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamPerformance {

	public int sum_using_intstream(int count, boolean isParallel) {
		startTime(true);
		IntStream intStream = IntStream.rangeClosed(0, count);
		if (isParallel)
			intStream.parallel();
		int sum = intStream.sum();
		timeTaken();
		return sum;
	}

	public int sum_using_list(List<Integer> inputList, boolean isParallel) {
		startTime(true);
		Stream<Integer> inputStream = inputList.stream();
		if (isParallel)
			inputStream.parallel();
		int sum = inputStream.mapToInt(Integer::intValue) // unboxing
				.sum();
		timeTaken();
		return sum;
	}

	public int sum_using_iterate(int n, boolean isParallel) {
		startTime();
		Stream<Integer> integerStream = Stream.iterate(0, i -> i + 1);
		if (isParallel)
			integerStream.parallel();
		int sum = integerStream.limit(n + 1) // includes the end value too
				.reduce(0, Integer::sum);
		timeTaken();
		return sum;
	}

}
