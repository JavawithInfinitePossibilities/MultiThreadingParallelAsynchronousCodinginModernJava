/**
 * 
 */
package com.sid.tutorials.multithreading.Java8.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sid.tutorials.multithreading.Java8.domain.Product;
import com.sid.tutorials.multithreading.Java8.services.InventoryService;
import com.sid.tutorials.multithreading.Java8.services.ProductInfoService;
import com.sid.tutorials.multithreading.Java8.services.ProductService;
import com.sid.tutorials.multithreading.Java8.services.ReviewService;

/**
 * @author Lenovo
 *
 */
@ExtendWith(MockitoExtension.class)
public class Chapter15ImplementExceptionHandlingRecoveryinProductService {
	@Mock
	private ProductInfoService pisMock;
	@Mock
	private ReviewService rsMock;
	@Mock
	private InventoryService isMock;

	@InjectMocks
	ProductService pscf;

	@Disabled
	@Test
	void retrieveProductDetailsWithInventory_approach2() {
		// given
		String productId = "ABC123";
		when(pisMock.retrieveProductInfo(any())).thenCallRealMethod();
		when(rsMock.retrieveReviews(any())).thenThrow(new RuntimeException("Exception Occurred"));
		when(isMock.retrieveInventory(any())).thenCallRealMethod();

		// when
		Product product = pscf.retrieveProductDetailsWithInventoryApproach2(productId);

		// then
		assertNotNull(product);
		assertTrue(product.getProductInfo().getProductOptions().size() > 0);
		product.getProductInfo().getProductOptions().forEach(productOption -> {
			assertNotNull(productOption.getInventory());
		});
		assertNotNull(product.getReview());
		assertEquals(0, product.getReview().getNoOfReviews());
	}

	@Test
	void retrieveProductDetailsWithInventory_approachException() {
		// given
		String productId = "ABC123";
		when(pisMock.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exception Occurred"));
		when(rsMock.retrieveReviews(any())).thenCallRealMethod();
		//when(isMock.retrieveInventory(any())).thenCallRealMethod();
		// then
		Assertions.assertThrows(RuntimeException.class,
				() -> pscf.retrieveProductDetailsWithInventoryApproach2(productId));
	}
}
