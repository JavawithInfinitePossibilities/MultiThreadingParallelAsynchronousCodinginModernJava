package com.sid.tutorials.multithreading.Java8.parallelstream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import com.sid.tutorials.multithreading.Java8.domain.DataDemo;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class StringTransformUsingForkJoinExample extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = 5490490581599138411L;
	private @NonNull List<String> inputList;
	private int noOfThreads;

	@Override
	protected List<String> compute() {
		List<String> resultList = new ArrayList<String>();
		if (inputList.size() > 1) {
		//if (inputList.size() > noOfThreads) {
			int midlist = inputList.size() / 2;
			ForkJoinTask<List<String>> leftFork = new StringTransformUsingForkJoinExample(inputList.subList(0, midlist),
					noOfThreads);
			ForkJoinTask<List<String>> rightFork = new StringTransformUsingForkJoinExample(inputList.subList(midlist,
					inputList.size()), noOfThreads);
			invokeAll(leftFork, rightFork);
			resultList.addAll(leftFork.join());
			resultList.addAll(rightFork.join());
		} else {
			resultList = inputList.stream().map((name) -> DataDemo.builder().build().addNameLengthTransform(name))
					.collect(Collectors.toList());
		}
		return resultList;
	}
}
