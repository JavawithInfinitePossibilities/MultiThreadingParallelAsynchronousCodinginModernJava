/**
 * 
 */
package com.sid.tutorials.multithreading.Java8.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.sid.tutorials.multithreading.Java8.domain.Product;
import com.sid.tutorials.multithreading.Java8.services.InventoryService;
import com.sid.tutorials.multithreading.Java8.services.ProductInfoService;
import com.sid.tutorials.multithreading.Java8.services.ProductService;
import com.sid.tutorials.multithreading.Java8.services.ReviewService;

/**
 * @author Lenovo
 *
 */
public class Chapter13CombiningStreamsandCompletableFutureTest {
	private ProductInfoService pis = new ProductInfoService();
	private ReviewService rs = new ReviewService();
	private InventoryService is = new InventoryService();
	ProductService pscf = new ProductService(pis, rs, is);

	@Disabled
	@Test
	void retrieveProductDetailsWithInventory() {
		// given
		String productId = "ABC123";

		// when
		Product product = pscf.retrieveProductDetailsWithInventory(productId);

		// then
		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() > 0);
		product.getProductInfo().getProductOptions().forEach(productOption -> {
			assertNotNull(productOption.getInventory());
		});
		assertNotNull(product.getReview());
	}

	
	@Test
	void retrieveProductDetailsWithInventory_approach2() {
		// given
		String productId = "ABC123";
		// when
		Product product = pscf.retrieveProductDetailsWithInventoryApproach2(productId);
		// then
		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() > 0);
		product.getProductInfo().getProductOptions().forEach(productOption -> {
			assertNotNull(productOption.getInventory());
		});
		assertNotNull(product.getReview());
	}
}
