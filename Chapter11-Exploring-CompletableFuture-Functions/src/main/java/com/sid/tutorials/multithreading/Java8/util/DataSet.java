package com.sid.tutorials.multithreading.Java8.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;

@Builder
public class DataSet {

	public static List<String> namesList() {
		return List.of("Bob", "Jamie", "Jill", "Rick");

	}

	public List<String> getNameList() {
		List<String> namesList = List.of("Bob", "Jamie", "Jill", "Rick", "Bob", "Jamie", "Jill", "Rick");
		return namesList;
	}

	public String addNameLengthTransform(String name) {
		CommonUtil.delay(500);
		return name.length() + "-" + name;
	}

	public static List<String> lowerCaseAlphabetList = List.of("a", "b", "c", "a", "d", "e", "f", "e", "g", "h", "i");

	public static List<Integer> generateIntegerList(int maxNumber) {
		return IntStream.rangeClosed(1, maxNumber).boxed().collect(Collectors.toList());
	}

	public static ArrayList<Integer> generateArrayList(int maxNumber) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		IntStream.rangeClosed(1, maxNumber).boxed().forEach((arrayList::add));
		return arrayList;
	}

	public static LinkedList<Integer> generateIntegerLinkedList(int maxNumber) {
		LinkedList<Integer> linkedList = new LinkedList<>();
		IntStream.rangeClosed(1, maxNumber).boxed().forEach((linkedList::add));
		return linkedList;
	}

	public static Set<Integer> generateIntegerSet(int maxNumber) {
		return IntStream.rangeClosed(1, maxNumber).boxed().collect(Collectors.toSet());
	}

	public static double generateRandomPrice() {
		int min = 50;
		int max = 100;
		return Math.random() * (max - min + 1) + min;
	}
}
