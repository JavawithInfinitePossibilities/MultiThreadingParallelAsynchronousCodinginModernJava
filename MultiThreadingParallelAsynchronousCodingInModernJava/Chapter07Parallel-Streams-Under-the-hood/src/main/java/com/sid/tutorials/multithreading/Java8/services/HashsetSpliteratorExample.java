package com.sid.tutorials.multithreading.Java8.services;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashsetSpliteratorExample {

	public Set<Integer> multiplyEachValue(Set<Integer> inputList, int multiplyValue, boolean isParallel) {
		startTime(true);
		Stream<Integer> integerStream = inputList.stream(); // sequential
		if (isParallel) {
			integerStream.parallel();
		}
		Set<Integer> resultList = integerStream.map(integer -> integer * multiplyValue).collect(Collectors.toSet());
		timeTaken();
		return resultList;
	}
}
