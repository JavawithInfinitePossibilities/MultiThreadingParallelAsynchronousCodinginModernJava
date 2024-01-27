/**
 * 
 */
package com.sid.tutorials.multithreading.Java8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;

import com.sid.tutorials.multithreading.Java8.services.ArrayListSpliteratorExample;
import com.sid.tutorials.multithreading.Java8.services.HashsetSpliteratorExample;
import com.sid.tutorials.multithreading.Java8.services.LinkedListSpliteratorExample;
import com.sid.tutorials.multithreading.Java8.util.DataSet;

import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;

/**
 * @author Lenovo
 *
 */
public class Chapter07ParallelStreamsUnderthehoodTest {
	ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();
	LinkedListSpliteratorExample linkedListSpliteratorExample = new LinkedListSpliteratorExample();
	HashsetSpliteratorExample hashsetSpliteratorExample = new HashsetSpliteratorExample();

	@Disabled
	@RepeatedTest(5)
	void multiplyEachValue() {
		boolean isParallel = false;
		// given
		int size = 1000000;
		ArrayList<Integer> inputList = DataSet.generateArrayList(size);
		// when
		List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(inputList, 2, isParallel);
		// then
		assertEquals(size, resultList.size());
	}

	@Disabled
	@RepeatedTest(5)
	void multiplyEachValueLinkedlist() {
		boolean isParallel = true;
		// given
		int size = 1000000;
		LinkedList<Integer> inputList = DataSet.generateIntegerLinkedList(size);
		// when
		List<Integer> resultList = linkedListSpliteratorExample.multiplyEachValue(inputList, 2, isParallel);
		// then
		assertEquals(size, resultList.size());
	}

	@RepeatedTest(1)
	void multiplyEachValueHashset() {
		boolean isParallel = false;
		// given
		int size = 17;
		Set<Integer> inputList = DataSet.generateIntegerSet(size);
		// when
		log("Input Set:" + inputList);
		Set<Integer> resultList = hashsetSpliteratorExample.multiplyEachValue(inputList, 2, isParallel);
		log("result Set:" + resultList);
		// then
		assertEquals(size, resultList.size());
	}

}
