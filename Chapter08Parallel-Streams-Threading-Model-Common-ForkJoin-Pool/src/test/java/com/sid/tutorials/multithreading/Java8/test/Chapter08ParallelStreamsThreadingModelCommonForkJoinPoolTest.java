/**
 * 
 */
package com.sid.tutorials.multithreading.Java8.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ForkJoinPool;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.sid.tutorials.multithreading.Java8.domain.Cart;
import com.sid.tutorials.multithreading.Java8.domain.CheckoutResponse;
import com.sid.tutorials.multithreading.Java8.domain.CheckoutStatus;
import com.sid.tutorials.multithreading.Java8.services.CheckoutService;
import com.sid.tutorials.multithreading.Java8.services.PriceValidatorService;
import com.sid.tutorials.multithreading.Java8.util.DataSet;
import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;

/**
 * @author Lenovo
 *
 */
public class Chapter08ParallelStreamsThreadingModelCommonForkJoinPoolTest {
	PriceValidatorService priceValidatorService = new PriceValidatorService();
	CheckoutService checkoutService = new CheckoutService(priceValidatorService);

	@Disabled
	@Test
	void no_Of_cores() {
		// given

		// when
		System.out.println("no of cores :" + Runtime.getRuntime().availableProcessors());

		// then
	}

	@Disabled
	@Test
	void parallelism() {
		// given

		// when
		log("parallelism :" + ForkJoinPool.getCommonPoolParallelism());

		// then
	}

	@Test
	void modify_parallelism() {
		boolean isParallel = true;
		// given
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");
		Cart cart = DataSet.createCart(100);
		// when
		CheckoutResponse checkoutResponse = checkoutService.checkout(cart, isParallel);
		// then
		assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
	}

	@Disabled
	@ParameterizedTest
	@ValueSource(ints = { 12 })
	void checkoutitems(int noOfCartItem) {
		boolean isParallel = false;
		int counter = 1;
		do {
			System.out.println("No of cart :" + noOfCartItem + " isParallel:" + isParallel);
			Cart cart = DataSet.createCart(noOfCartItem);
			// when
			CheckoutResponse checkoutResponse = checkoutService.checkout(cart, isParallel);
			// then
			if (noOfCartItem == 6 || noOfCartItem == 4) {
				assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
			} else if (noOfCartItem == 12) {
				assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
			}
			if (counter == 1) {
				isParallel = true;
			} else {
				isParallel = false;
			}
			counter++;
		} while (isParallel);
	}
}
