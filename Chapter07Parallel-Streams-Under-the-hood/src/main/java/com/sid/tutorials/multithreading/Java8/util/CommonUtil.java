package com.sid.tutorials.multithreading.Java8.util;

import org.apache.commons.lang3.time.StopWatch;

import static java.lang.Thread.sleep;
import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;

public class CommonUtil {

	public static StopWatch stopWatch = new StopWatch();

	public static void delay(long delayMilliSeconds) {
		try {
			sleep(delayMilliSeconds);
		} catch (Exception e) {
			log("Exception is :" + e.getMessage());
		}
	}

	public static void startTime() {
		stopWatch.start();
	}

	public static void timeTaken() {
		stopWatch.stop();
		log("Time taken : " + stopWatch.getTime());
	}

	public static void startTime(boolean b) {
		stopWatch.reset();
		stopWatch.start();
	}
}
