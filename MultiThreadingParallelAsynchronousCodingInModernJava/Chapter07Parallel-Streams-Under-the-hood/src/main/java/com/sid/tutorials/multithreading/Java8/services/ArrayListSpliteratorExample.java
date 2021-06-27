package com.sid.tutorials.multithreading.Java8.services;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayListSpliteratorExample {

	public List<Integer> multiplyEachValue(ArrayList<Integer> inputList, int multiplyValue, boolean isParallel) {
		startTime(true);
		Stream<Integer> integerStream = inputList.stream(); // sequential
		if (isParallel) {
			integerStream.parallel();
		}
		List<Integer> resultList = integerStream.map(integer -> integer * multiplyValue).collect(Collectors.toList());
		timeTaken();
		return resultList;
	}

}
