package com.sid.tutorials.multithreading.Java8.util;

import java.util.List;

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
}
