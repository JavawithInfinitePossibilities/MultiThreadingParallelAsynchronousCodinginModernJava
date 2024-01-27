package com.sid.tutorials.multithreading.Java8.executorservicesdemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.sid.tutorials.multithreading.Java8.domain.Product;
import com.sid.tutorials.multithreading.Java8.domain.ProductInfo;
import com.sid.tutorials.multithreading.Java8.domain.Review;
import com.sid.tutorials.multithreading.Java8.services.ProductInfoService;
import com.sid.tutorials.multithreading.Java8.services.ReviewService;
import com.sid.tutorials.multithreading.Java8.util.CommonUtil;
import com.sid.tutorials.multithreading.Java8.util.LoggerUtil;

public class ProductServiceExecutorservicesDemo {
	private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
			.availableProcessors());

	private ProductInfoService productInfoService;
	private ReviewService reviewService;

	public ProductServiceExecutorservicesDemo(ProductInfoService productInfoService, ReviewService reviewService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
	}

	public Product retrieveProductDetails(String productId) throws InterruptedException, ExecutionException,
			TimeoutException {
		CommonUtil.stopWatch.start();
		Future<ProductInfo> productInfoFuture = executorService.submit(() -> productInfoService.retrieveProductInfo(
				productId));
		Future<Review> reviewFuture = executorService.submit(() -> reviewService.retrieveReviews(productId));
		//ProductInfo productInfo = productInfoFuture.get(); // blocking call
		ProductInfo productInfo = productInfoFuture.get(2, TimeUnit.SECONDS);
		//Review review = reviewFuture.get(); // blocking call
		Review review = reviewFuture.get(1, TimeUnit.SECONDS);
		CommonUtil.stopWatch.stop();
		LoggerUtil.log("Total Time Taken : " + CommonUtil.stopWatch.getTime());
		return new Product(productId, productInfo, review);
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		ProductInfoService productInfoService = new ProductInfoService();
		ReviewService reviewService = new ReviewService();
		ProductServiceExecutorservicesDemo productService = new ProductServiceExecutorservicesDemo(productInfoService,
				reviewService);
		String productId = "ABC123";
		Product product = productService.retrieveProductDetails(productId);
		LoggerUtil.log("Product is " + product);
		executorService.shutdown();
	}
}
