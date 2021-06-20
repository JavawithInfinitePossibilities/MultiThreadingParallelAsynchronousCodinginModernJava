/**
 * 
 */
package com.sid.tutorials.multithreading.Java8.domain;

import java.util.List;

import com.sid.tutorials.multithreading.Java8.util.CommonUtil;

import lombok.Builder;

/**
 * @author Lenovo
 *
 */
@Builder
public class DataDemo {

	public List<String> getNameList() {
		List<String> namesList = List.of("Bob", "Jamie", "Jill", "Rick", "Bob", "Jamie", "Jill", "Rick");
		return namesList;
	}

	public String addNameLengthTransform(String name) {
		CommonUtil.delay(500);
		return name.length() + "-" + name;
	}
}
