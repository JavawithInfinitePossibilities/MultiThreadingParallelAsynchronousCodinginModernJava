/**
 * 
 */
package com.sid.tutorials.multithreading.Java8.parallelstream;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.stopWatch;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import com.sid.tutorials.multithreading.Java8.domain.DataDemo;
import com.sid.tutorials.multithreading.Java8.util.LoggerUtil;

/**
 * @author Lenovo
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		stopWatch.start();
		List<String> namesList = DataDemo.builder().build().getNameList();
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		LoggerUtil.log("namesList : " + namesList);
		LoggerUtil.log("availableProcessors : " + availableProcessors);
		StringTransformUsingForkJoinExample example = new StringTransformUsingForkJoinExample(namesList,
				availableProcessors);
		ForkJoinPool forkJoinPool = new ForkJoinPool(availableProcessors);
		List<String> namesListUpperCase = forkJoinPool.invoke(example);
		stopWatch.stop();
		LoggerUtil.log("Time taken : " + stopWatch.getTime());
		LoggerUtil.log("namesListUpperCase : " + namesListUpperCase);
	}

}
