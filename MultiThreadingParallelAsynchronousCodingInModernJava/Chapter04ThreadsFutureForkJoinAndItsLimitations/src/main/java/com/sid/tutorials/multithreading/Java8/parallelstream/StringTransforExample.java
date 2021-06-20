package com.sid.tutorials.multithreading.Java8.parallelstream;

import java.util.List;
import java.util.stream.Collectors;

import com.sid.tutorials.multithreading.Java8.domain.DataDemo;
import com.sid.tutorials.multithreading.Java8.util.LoggerUtil;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.*;

public class StringTransforExample {

	public static void main(String[] args) {
		stopWatch.start();
		List<String> namesList = DataDemo.builder().build().getNameList();
		System.out.println("namesList : " + namesList);
		List<String> namesListUpperCase = namesList.stream()
				.map((name) -> DataDemo.builder().build().addNameLengthTransform(name))
				.collect(Collectors.toList());
		stopWatch.stop();
		LoggerUtil.log("Time taken : " + stopWatch.getTime());
		LoggerUtil.log("namesListUpperCase : " + namesListUpperCase);
	}
}
