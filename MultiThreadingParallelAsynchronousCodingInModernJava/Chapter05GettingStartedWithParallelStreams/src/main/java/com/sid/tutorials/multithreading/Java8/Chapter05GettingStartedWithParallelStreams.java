/**
 * 
 */
package com.sid.tutorials.multithreading.Java8;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.startTime;
import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.timeTaken;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sid.tutorials.multithreading.Java8.util.DataSet;
import com.sid.tutorials.multithreading.Java8.util.LoggerUtil;

import lombok.Builder;

/**
 * @author Lenovo
 *
 */
@Builder
public class Chapter05GettingStartedWithParallelStreams {

	public void parallelAndSequential(boolean isParallel) {
		DataSet dataSet = DataSet.builder().build();
		List<String> namesList = dataSet.getNameList();
		List<String> namesListUpperCase=null;
		System.out.println("namesList : " + namesList);
		Stream<String> nameStream = namesList.stream();
		if (isParallel) {
			nameStream = nameStream.parallel();
		}
		startTime(true);
		//namesListUpperCase = nameStream.map(name -> dataSet.addNameLengthTransform(name)).collect(Collectors.toList());
		// Example of object reference
		namesListUpperCase = nameStream.map(dataSet::addNameLengthTransform).collect(Collectors.toList());
		timeTaken();
		LoggerUtil.log("namesListUpperCase : " + namesListUpperCase);
	}

}
