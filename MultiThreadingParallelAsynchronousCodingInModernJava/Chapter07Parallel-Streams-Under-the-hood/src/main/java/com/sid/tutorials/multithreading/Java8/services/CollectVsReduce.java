package com.sid.tutorials.multithreading.Java8.services;

import static com.sid.tutorials.multithreading.Java8.util.DataSet.*;
import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;

import java.util.List;
import java.util.stream.Collectors;

import com.sid.tutorials.multithreading.Java8.util.DataSet;

public class CollectVsReduce {

	public static String collect() {
		List<String> list = DataSet.namesList();
		String result = list.parallelStream().collect(Collectors.joining());
		return result;
	}

	public static String reduce() {
		List<String> list = DataSet.namesList();
		String result = list.parallelStream().reduce("", (s1, s2) -> s1 + s2);
		return result;
	}

	public static void main(String[] args) {
		log("collect : " + collect());
		log("reduce : " + reduce());
	}
}
