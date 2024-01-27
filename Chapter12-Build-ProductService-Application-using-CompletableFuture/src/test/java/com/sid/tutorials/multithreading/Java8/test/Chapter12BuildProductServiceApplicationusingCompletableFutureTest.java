/**
 * 
 */
package com.sid.tutorials.multithreading.Java8.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.sid.tutorials.multithreading.Java8.domain.Product;
import com.sid.tutorials.multithreading.Java8.services.ProductInfoService;
import com.sid.tutorials.multithreading.Java8.services.ProductService;
import com.sid.tutorials.multithreading.Java8.services.ReviewService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lenovo
 *
 */
public class Chapter12BuildProductServiceApplicationusingCompletableFutureTest {
	private ProductInfoService pis = new ProductInfoService();
	private ReviewService rs = new ReviewService();
	ProductService pscf = new ProductService(pis, rs);

	@Disabled
	@Test
	void retrieveProductDetails() {
		// given
		String productId = "ABC123";

		// when
		Product product = pscf.retrieveProductDetails(productId);

		// then
		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() > 0);
		assertNotNull(product.getReview());
	}
	
	@Test
	void retrieveProductDetailsApproach2() {
		// given
		String productId = "ABC123";

		// when
		pscf.retrieveProductDetailsApproach2(productId).thenAccept((product)->{
			assertNotNull(product);
			assertTrue(product.getProductInfo().getProductOptions().size() > 0);
			assertNotNull(product.getReview());
		}).join();

		// then
		
	}
}
