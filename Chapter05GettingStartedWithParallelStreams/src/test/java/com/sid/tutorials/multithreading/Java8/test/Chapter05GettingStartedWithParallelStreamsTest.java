/**
 * 
 */
package com.sid.tutorials.multithreading.Java8.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.sid.tutorials.multithreading.Java8.Chapter05GettingStartedWithParallelStreams;

/**
 * @author Lenovo
 *
 */
public class Chapter05GettingStartedWithParallelStreamsTest {

	@ParameterizedTest
	@ValueSource(booleans = { false, true })
	public void testParalleltest(boolean isParallel) {
		Chapter05GettingStartedWithParallelStreams.builder().build().parallelAndSequential(isParallel);
	}
}
